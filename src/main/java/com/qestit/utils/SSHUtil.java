package com.qestit.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.jcraft.jsch.*;

public class SSHUtil {
    private Session session;
 // Establishes an SSH connection using the provided host, port, username, and password
    public void connect(String host, int port, String user, String password) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setPassword(password);

            // Recommended to use more secure host key checking
            session.setConfig("StrictHostKeyChecking", "no");

            LogUtils.info("Connecting to SSH server at " + host + ":" + port);
            session.connect();
            LogUtils.info("Connected successfully.");
        } catch (JSchException e) {
            LogUtils.error("Failed to connect to " + host + ":" + port, e);
            throw new RuntimeException("SSH connection failure", e);
        }
    }

    public String executeCommand(String command) throws IOException {
        StringBuilder output = new StringBuilder();
        ChannelExec channel = null;
        try {
            LogUtils.info("Executing command: " + command);
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.setErrStream(errorStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            output.append(new String(responseStream.toByteArray()));
            String errorResponse = new String(errorStream.toByteArray());
            if (!errorResponse.isEmpty()) {
                LogUtils.error("Error in command execution: " + errorResponse);
            }

            LogUtils.info("Command executed. Response: " + output);
            return output.toString();
        } catch (JSchException | InterruptedException e) {
            LogUtils.error("Command execution failed: " + command, e);
            throw new RuntimeException("SSH command execution failure", e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    public void disconnect() {
        if (session != null) {
            LogUtils.info("Disconnecting from SSH server.");
            session.disconnect();
            LogUtils.info("Disconnected.");
        }
    }
}

