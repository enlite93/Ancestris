/**
 * Reports are Freeware Code Snippets
 *
 * This report is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */
package genjfr.app.tools.webbook.transfer;

import genjfr.app.tools.webbook.Log;
import genjfr.app.tools.webbook.WebBookParams;
import genjfr.app.tools.webbook.WebBookStarter;
import genjfr.app.tools.webbook.creator.WebHelper;

import java.io.*;
import java.util.*;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Cancellable;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.Task;
import org.openide.util.TaskListener;

public class FTPLoader {

    private Log log = null;
    private final static RequestProcessor RP = new RequestProcessor("interruptible tasks", 1, true);
    private RequestProcessor.Task theTask = null;
    private FTPUpload ftpu = null;
    //
    public String localfile;
    public String targetfile;
    private String host = "";
    private String user = "";
    private String password = "";
    private File localdir = null;
    private String targetdir = "";
    private FTPRegister uploadRegister = null;
    private String shell = "";

    public FTPLoader(WebBookParams wp, WebHelper wh, FTPRegister uploadRegister) {
        this.host = wp.param_FTP_site;
        this.user = wp.param_FTP_user;
        this.password = wp.param_FTP_password;
        this.localdir = wh.getDir();
        this.targetdir = wp.param_FTP_dir;
        this.uploadRegister = uploadRegister;
        this.shell = wp.param_FTP_exec;
        log = new Log(wp.param_FTP_log, NbBundle.getMessage(WebBookStarter.class, "OpenIDE-Module-Name")
                + "_" + NbBundle.getMessage(FTPLoader.class, "EXEC_uploading") + " " + wh.gedcom.getName());
    }

    // Starter
    public synchronized void run() {
        final ProgressHandle ph = ProgressHandleFactory.createHandle(NbBundle.getMessage(FTPLoader.class, "TASK_UploadExecution"), new Cancellable() {

            public boolean cancel() {
                return handleCancel();
            }
        });

        Runnable runnable = new Runnable() {

            public synchronized void run() {
                // Collect all files to send across
                log.timeStamp();
                log.write(NbBundle.getMessage(FTPLoader.class, "TASK_UploadExecutionStart"));
                List<File> localFiles = getFilesRecursively(localdir);
                Collections.sort(localFiles);
                ph.start();

                // Put bulk of files giving the log and the progress window id as reference
                ftpu = new FTPUpload(host, user, password, localFiles, localdir.getAbsolutePath(), targetdir, log, uploadRegister, ph);
                ftpu.run();
                if (log.endSuccessful) {
                    runUserShell();
                }
                log.write(" ");
                log.write(" ");
                log.write(" ");
                log.timeStamp();
                if (log.endSuccessful) {
                    log.write(log.NORMAL, NbBundle.getMessage(FTPLoader.class, "TASK_UploadExecutionSuccess"));
                } else {
                    log.write(log.ERROR, NbBundle.getMessage(FTPLoader.class, "TASK_UploadExecutionFailed"));
                }
            }
        };


        theTask = RP.create(runnable); //the task is not started yet
        theTask.addTaskListener(new TaskListener() {

            public void taskFinished(Task task) {
                ph.finish();
                log.close();
            }
        });

        theTask.schedule(0); //start the task

        return;
    }

    private boolean handleCancel() {
        if (null == theTask) {
            return false;
        }
        log.write(log.ERROR, NbBundle.getMessage(FTPLoader.class, "TASK_UploadExecutionStopped"));
        ftpu.cancel();
        theTask.cancel();
        RP.stop();
        return true;
    }

    /**
     * Get local files
     */
    protected List<File> getFilesRecursively(File dir) {
        List<File> filesRet = new ArrayList<File>();
        File[] strs = dir.listFiles();
        if (strs == null) {
            return null;
        }
        List<File> files = Arrays.asList(strs);
        for (Iterator it = files.iterator(); it.hasNext();) {
            File file = (File) it.next();
            if (file.isDirectory()) {
                filesRet.addAll(getFilesRecursively(file));
            } else {
                filesRet.add(file);
            }
        }
        return filesRet;
    }

    private void runUserShell() {
        log.write("   ");
        log.write("===========================");
        log.write(NbBundle.getMessage(FTPLoader.class, "upload_shell"));
        if (!shell.isEmpty()) {
            try {
                log.write(NbBundle.getMessage(FTPLoader.class, "shell_launch", shell));
                Runtime.getRuntime().exec(shell);
                log.write(NbBundle.getMessage(FTPLoader.class, "shell_cannotwait"));
            } catch (IOException e) {
                log.write(NbBundle.getMessage(FTPLoader.class, "shell_error", new String[]{shell, e.getMessage()}));
            }
        } else {
            log.write(NbBundle.getMessage(FTPLoader.class, "shell_none"));
        }

    }
}


