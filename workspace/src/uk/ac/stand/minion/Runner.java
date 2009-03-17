package uk.ac.stand.minion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import translator.*;
import translator.normaliser.NormaliserSpecification;
import translator.solver.Minion;

// Initial code from Andrea Rendl
public class Runner {

	private String minionOutputFileName = "out.chris";

	private String minionExecPath = System.getProperty("user.dir") + File.separator +"minion";

	Translator translator;

	private Minion solver;
	private TranslationSettings settings;

	public Runner(String essenceIn, String essenceParam) {
		this.essenceInput = essenceIn;
		this.essenceParamInput = essenceParam;
		
		this.settings = new TranslationSettings();
		this.translator = new Translator(settings);
	}

	private String essenceInput = null;

	private String essenceParamInput = null;

	private String solverOutput = ""; // The code to execute

	protected boolean parse() {

		String problemSpec = essenceInput;
		String parameterSpec = essenceParamInput;

		boolean parsingSuccessfull = false;

		if (parameterSpec.equalsIgnoreCase(""))
			parsingSuccessfull = translator.parse(problemSpec);

		else
			parsingSuccessfull = translator.parse(problemSpec, parameterSpec);

		if (parsingSuccessfull) {
			writeOnMessageOutput("Parsing OK.\n");
		} else {
			writeOnMessageOutput("===================== ERROR ======================\n"
					+ "Parse Error.\n"
					+ this.translator.getErrorMessage()
					+ "\n"
					+ "===============================================\n");
			System.err.println(this.translator.getErrorMessage());
			return false;
		}
		return true;
	}

	protected boolean flatten() {

		if (!normalise())
			return false;
		boolean flattening = this.translator.flatten(this.solver);

		if (flattening) {
			// System.out.println("Flattening is fertig.");
			// System.out.println(this.translator.printAdvancedModel());
			writeOnMessageOutput("Flattened constraints for target solver "
					+ solver.getSolverName() + "\n");
			// System.out.println("Flattening is WIRKLICK fertig.");
		} else {
			writeOnMessageOutput("================== ERROR ======================\n"
					+ "Flattening for target solver "
					+ solver.getSolverName()
					+ " failed.\n"
					+ this.translator.getErrorMessage()
					+ "\n"
					+ "===============================================\n");
			return false;
		}
		return true;
	}

	protected boolean normalise() {

		if (!parse())
			return false;
		if (this.translator.normalise(NormaliserSpecification.NORMALISE_FULL)) {
			// System.out.println(this.translator.printAdvancedModel());
			this
					.writeOnMessageOutput("Full Normalisation (ordering, evaluation, restructuring) successful.\n");
		} else {
			this
					.writeOnMessageOutput("================== ERROR ======================\n"
							+ "Full Normalisation (ordering, evaluation, restructuring) failed.\n"
							+ this.translator.getErrorMessage()
							+ "\n"
							+ "===============================================\n");
			return false;
		}

		return true;
	}

	protected boolean toMinion(String s) {

		this.solver = new Minion();

		if (!flatten())
			return false;
		// System.out.println("Flattened the stuff, now tailoring.");
		boolean tailoring = this.translator.tailorTo(solver);

		if (tailoring) {
			solverOutput = this.translator.getTargetSolverInstance();
			// System.out.println(this.translator.getTargetSolverInstance());
			writeOnMessageOutput("Tailored model to target solver "
					+ solver.getSolverName() + "\n");
		} else {
			writeOnMessageOutput("===================== ERROR ======================\n"
					+ "Tailoring to target solver "
					+ solver.getSolverName()
					+ " failed.\n"
					+ this.translator.getErrorMessage()
					+ "\n"
					+ "===============================================\n");
			return false;
		}
		return true;
	}

	/**
	 * Executes Minion: first the string from the solver input tab is read and
	 * written into a file. Then that file is given as input to Minion with
	 * respect to the solving settings given (amount of solutions etc).
	 * 
	 */

	private void writeOnMessageOutput(String message) {
		System.out.println(message);
	}
	
	/**
	 * Starts Minion and enables the Buffered output and error streams to be fetched using the getter methods. 
	 * Also allows for tests of whether the process is still running.
	 * 
	 * @return the running Minion Process
	 */
	public Process runMinionInteractive() {
		try {
			String outputFileName = minionOutputFileName;

			// -------------- write the output into a file ---------------
			writeOnMessageOutput("Creating Minion file: " + outputFileName
					+ "\n");
			File file = new File(outputFileName);
			if (file.createNewFile())
				;
			FileWriter writer = new FileWriter(file);

			if (!file.canRead())
				writeOnMessageOutput("Cannot read file: \n " + file.toString()
						+ "\nPlease change reading permissions.");
			else if (!file.canWrite())
				writeOnMessageOutput("Cannot write file: \n " + file.toString()
						+ "\nPlease change writing permissions.");

			writer.write(this.solverOutput);

			writer.flush();
			writer.close();

			// ----------- set up execution command with arguments ----------

			String[] commandArguments;

			commandArguments = new String[] { minionExecPath, outputFileName };

			// -------------execute process-------------------------------
			for (String s : commandArguments)
				System.out.print(s + " ");
			System.out.println();

			Process process = Runtime.getRuntime().exec(commandArguments);
			
			return process;

		} catch (Exception e) {
			writeOnMessageOutput("Could not run Minion:\n"
					+ e.getMessage()
					+ "\n"
					+ "You can change the path to your Minion executable in 'Settings'.\n");
			return null;
		}
	}

	protected String runMinion() {

		try {
			String outputFileName = minionOutputFileName;

			// -------------- write the output into a file ---------------
			writeOnMessageOutput("Creating Minion file: " + outputFileName
					+ "\n");
			File file = new File(outputFileName);
			if (file.createNewFile())
				;
			FileWriter writer = new FileWriter(file);

			if (!file.canRead())
				writeOnMessageOutput("Cannot read file: \n " + file.toString()
						+ "\nPlease change reading permissions.");
			else if (!file.canWrite())
				writeOnMessageOutput("Cannot write file: \n " + file.toString()
						+ "\nPlease change writing permissions.");

			writer.write(this.solverOutput);
			// String message = "Saved Minion input in file: \n " +
			// file.getName() + "." +"\n";
			// writeOnMessageOutput(message);

			writer.flush();
			writer.close();

			// ----------- set up execution command with arguments ----------

			String[] commandArguments;

			commandArguments = new String[] { minionExecPath, outputFileName };

			// -------------execute process-------------------------------
			for (String s : commandArguments)
				System.out.print(s + " ");
			System.out.println();

			Process process = Runtime.getRuntime().exec(commandArguments);

			// first read the input and the error stream
			InputStream inputStream = process.getInputStream();
			InputStream errorStream = process.getErrorStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(
					inputStream));
			BufferedReader error = new BufferedReader(new InputStreamReader(
					errorStream));

			// -----------reading Minion's output-------------------------
			String line;
			String solverOutputString = "";
			while ((line = input.readLine()) != null) {
				solverOutputString = solverOutputString + line + "\n";
			}
			line = "";
			StringBuffer solverErrorMessage = new StringBuffer("");
			while ((line = error.readLine()) != null) {
				solverErrorMessage.append(line + "\n");
			}

			// then wait for process (to conform with Microsoft Windows)
			process.waitFor();

			int exitValue = process.exitValue();

			// -------- process finished, so write Minion's output/error
			// messages to GUI-------------------
			if (exitValue == 0) {
				input.close();
				return (solverOutputString);

			} else {
				System.out
						.println("$ Error in executing Minion.\n$ See system messages below for details.\n");

				if (solverOutputString.length() > 1)
					this
							.writeOnMessageOutput("Minion returned the following error message:\n"
									+ solverOutputString);
				input.close();

				this
						.writeOnMessageOutput("Minion returned the following error message:\n"
								+ solverErrorMessage);
				error.close();

				return "error";
			}

		} catch (Exception e) {
			writeOnMessageOutput("Could not run Minion:\n"
					+ e.getMessage()
					+ "\n"
					+ "You can change the path to your Minion executable in 'Settings'.\n");
			return "error";
		}
	}

	public String getMinionInput() {
		return solverOutput;
	}

}
