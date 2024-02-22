/*
 * Copyright (c) 2023 QESTIT
 * QesteniumX
 */

package com.qestit.helpers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelpers {

	public DatabaseHelpers() {
		super();
	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws SQLException {

		// For example: jdbc:mysql://localhost:3306/ecommerce
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);

		return conn;
	}

	// Capture a snapshot (backup) of the MySQL database
	public static void captureSnapshot(String backupFilePath, String hostName, String dbName, String userName,
			String password) throws IOException, InterruptedException {
		try {
			// Build the mysqldump command to create a backup file
			String command = String.format("mysqldump --user=%s --password=%s --host=%s --port=3306 %s > %s", userName,
					password, hostName, dbName, backupFilePath);

			// Execute the command in a separate process
			Process process = Runtime.getRuntime().exec(command);

			// Wait for the process to complete and get the exit code
			int exitCode = process.waitFor();

			// Check the exit code and print appropriate messages
			if (exitCode == 0) {
				System.out.println("Database snapshot captured successfully.");
			} else {
				System.err.println("Error capturing database snapshot. Exit code: " + exitCode);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("Error capturing database snapshot: " + e.getMessage());
		}
	}

	// Restore a MySQL database from a backup file
	public static void restoreDatabase(String backupFilePath, String hostName, String dbName, String userName,
			String password) throws IOException, InterruptedException {
		try {
			// Build the mysql command to restore the database from a backup file
			String command = String.format("mysql --user=%s --password=%s --host=%s --port=3306 %s < %s", userName,
					password, hostName, dbName, backupFilePath);

			// Execute the command in a separate process
			Process process = Runtime.getRuntime().exec(command);

			// Wait for the process to complete and get the exit code
			int exitCode = process.waitFor();

			// Check the exit code and print appropriate messages
			if (exitCode == 0) {
				System.out.println("Database restored successfully.");
			} else {
				System.err.println("Error restoring database. Exit code: " + exitCode);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("Error restoring database: " + e.getMessage());
		}
	}
}
