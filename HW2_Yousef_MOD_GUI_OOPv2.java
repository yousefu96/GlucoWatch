/*
 * Author: Usman Yousef
 * Date: 10-07-2021
 * Description: GlucoWatch
 * v4:
 * implements the use of a patient class for OOP
 * Constraints: 
 * does not validate string input of name to ensure no number/special characters
 * does not invalidate input of alphabetic/special characters when prompted to enter an int/double
 * limited in array size for retaining patient data or static
 * number/max of patients is unknown
 * initialized glucose reading of a patient that has not fasted (0) is recorded as the lowest level glucose patient 
 */
import java.util.ArrayList;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
public class HW2_Yousef_MOD_GUI_OOPv2
{
		//global working variables
		static DecimalFormat DF = new DecimalFormat("##.##");
		static String input = " ";
		
		//patient
		static ArrayList<Patient> pList = new ArrayList<Patient>();
		//static Patient [] pt = new Patient[100];
		static int pFoundAt = 0;
		static int pNum = 0;
		
		//final reading data
		static double totalPatients = 0.0;
		static double hLPatients = 0.0;
		static double nLPatients = 0.0;
		static double lLPatients = 0.0;
		static String domLvl = " ";
		
		//global limit variables
		static int lGluc = 80;
		static int hGluc = 130;

//Questions:
//Should I be using global variables? Isn't this bad practice?
//How do I declare the array size for the system to reserve enough memory?
//Should an object be created locally and passed instead of using global variables?
		
	public static void main(String[] args) 
	{
		//declarations
			//working variables
			int start = 0;
			int menuSelection = 0;
			int patientFound = 0;
			int foundAt = 0;
		
		start = GetStartSelection(start);
		
		if(start == 1)
		{
			//InitializePatientAge(); - used to play with random function
			while(menuSelection != 4)
			{
				menuSelection = DisplayMenu(menuSelection);
				switch(menuSelection)
				{
				case 1: //collects patient data 
					Patient pt = new Patient();
					pt.GetPatientName();
					pt.GetPatientSSN();
					pt.GetPatientAge();
					pt.GetFastingStatus();
					if(pt.pFCheck == 1)
					{
						pt.GetGlucoseReading();
					}//end if(pt[pNum].pFCheck == 1)
					else
					{
						DisplayFastingReq();
					}//end else
					
					pList.add(pt);
					pNum++;
					totalPatients++;
					//start = GetContinue(start);
					break;
				case 2: //displays all patient info
					for(int i = 0; i < pNum; i++)
					{
						pList.get(i).DisplayPatientReport();
					}//end for(int i = 0; i < pNum; i++)
					break;
				case 3: //search for a patient
					patientFound = FindPatient(patientFound);
					if(patientFound == 1)
					{
						pList.get(pFoundAt).DisplayPatientReport();
						patientFound = 0;
					}//end (patientFound == 1)
					else
					{
						JOptionPane.showMessageDialog(null, "Patient not found! Add the patient or check the SSN and try again!");
					}//end else
					break;
				case 4: //exit the program - not executing
					DisplayFinalReports();
					DisplayGoodbye();
					break;
				}//end switch(menuSelection)
			}//end while(menuSelection != 4)
		}//end if(start == 1)
		else
		{
			DisplayGoodbye();
		}//end else
	}//end public static void main(String[] args)
//=========================================================================================================================================================================================================================	
	static class Patient
	{
		//patient object
		//attribute declarations
		String pSSN;
		String pName;
		String pStatus; //glucose reading evaluation
		String pFStatus; //patient fasting status
		int pAge;
		int pFCheck;
		double pGReading;
		
		//constructors without arguments
		//what are constructors?
		//why are they important?
		//what does this block of code to aside from initialize?
		Patient()
		{
			pSSN = " ";
			pName = " ";
			pStatus = " ";
			pFStatus = " ";
			pAge = 0;
			pFCheck = 0;
			pGReading = 0;
		}//end Patient()
		
		//what is the difference here?
		//why do we use this.example?
		Patient(String pSSN, String pName, String pStatus, 
				int pAge, int pFCheck, double pGReading)
		{
			this.pSSN = pSSN;
			this.pName = pName;
			this.pStatus = pStatus;
			this.pAge = pAge;
			this.pFCheck = pFCheck;
			this.pGReading = pGReading;
		}//end constructors with arguments
		
		//methods
		//=========================================================================================================================================================================================================================	
		void GetPatientName() 
		{
			pName = JOptionPane.showInputDialog("Enter the patient's name: ");
		}//void GetPatientName()
		//=========================================================================================================================================================================================================================	
		void GetPatientSSN()
		{
			pSSN = JOptionPane.showInputDialog(null, "Enter the patient's SSN: ");
		}//end void GetPatientSSN()
		//=========================================================================================================================================================================================================================	
		void GetPatientAge() 
		{
			input = JOptionPane.showInputDialog("Enter the patient's age: ");
			pAge = Integer.parseInt(input);
			ValidatePAge();
		}//end void GetPatientAge()  
		//=========================================================================================================================================================================================================================	
		void ValidatePAge()
		{
			if (pAge < 0 || pAge > 200)
			{
				input = JOptionPane.showInputDialog("Invalid age!" +
													"\nEnter the patient's age: ");
				pAge = Integer.parseInt(input);
			}//end if (pAge[pNum] < 0 || pAge[pNum] > 200)
		}//end void ValidatePAge()
		//=========================================================================================================================================================================================================================	
		void GetFastingStatus()
		{
			input = JOptionPane.showInputDialog("Has the patient fasted for a minimum of 12 hours?" +
												"\n1 - Yes" +
												"\n0 - No");
			pFCheck = Integer.parseInt(input);
			ValidatePFCheck();
			if(pFCheck == 1)
			{
				pFStatus = "Fasting";
			}//end if(pFCheck[pNum] == 1)
			else
			{
				pFStatus = "Not fasting";
			}//end else
		}//end void GetFastingStatus()
		//=========================================================================================================================================================================================================================	
		void ValidatePFCheck()
		{
			while (pFCheck != 1 && pFCheck != 0)
			{
				input = JOptionPane.showInputDialog("Invalid input!" +
													"\nHas the patient fasted for a minimum of 12 hours?" +
													"\n1 - Yes" +
													"\n0 - No");
				pFCheck = Integer.parseInt(input);
			}//end while (pFCheck != 1 && pFCheck != 0)
		}//end void ValidatePFCheck()
		//=========================================================================================================================================================================================================================	
		void GetGlucoseReading()
		{
			input = JOptionPane.showInputDialog("Enter the patient's glucose reading: ");
			pGReading = Double.parseDouble(input);
			ValidatePGReading();
			EvaluateReading();
		}//end void GetGlucoseReading()
		//=========================================================================================================================================================================================================================	
		void ValidatePGReading()
		{
			while(pGReading == 0 || pGReading < 0)
			{
				input = JOptionPane.showInputDialog("Invalid input! Enter the patient's glucose reading: ");
				pGReading = Double.parseDouble(input);
			}//end while(pGReading == 0 || pGReading < 0)
		}//end void ValidatePGReading()
		//=========================================================================================================================================================================================================================	
		void EvaluateReading()
		{
			if(pGReading > 0 && pGReading < lGluc)
			{
				lLPatients++;
				pStatus = "The patient's glucose level is low!";
			}//end if(pGReading > 0 && pGReading < lGluc)
			else if(pGReading >= lGluc && pGReading <= hGluc)
			{
				nLPatients++;
				pStatus = "The patient's glucose level is normal!";
			}//end else if(pGReading >= lGluc && pGReading <= hGluc)
			else
			{
				hLPatients++;
				pStatus = "The patient's glucose level is too high!";
			}//end else
		}//end void EvaluateReading()
		//=========================================================================================================================================================================================================================	
		void DisplayPatientReport()
		{
			JOptionPane.showMessageDialog(null, "Patient Report: " +
												"\nPatient: " + pName +
												"\nSSN: " + pSSN +
												"\nAge: " + pAge +
												"\nFasting Status: " + pFStatus +
												"\nGlucose Reading: " + DF.format(pGReading) +
												"\nStatus: " + pStatus);
		}//end public static void DisplayPatientReport()
		//=========================================================================================================================================================================================================================			

		//=========================================================================================================================================================================================================================	
	}//end class Patient
//=========================================================================================================================================================================================================================	
	public static Integer GetStartSelection(int start) 
	{
		input = JOptionPane.showInputDialog("Would you like to start the program?" +
											"\n1 - Yes" +
											"\n0 - No");
		start = Integer.parseInt(input);
		ValidateStart(start);
		return start;
	}//end public static Integer GetStartSelection(int start) 
//=========================================================================================================================================================================================================================		
	public static Integer ValidateStart(int start) 
	{
		while (start != 1 && start != 0)
		{
			input = JOptionPane.showInputDialog("Invalid input!" +
												"\nWould you like to continue the program?" +
												"\n1 - Yes" +
												"\n0 - No");
			start = Integer.parseInt(input);
		}//end while (start != 1 && start != 0)
		return start;
	}//end public static Integer ValidateStart(int start)
//=========================================================================================================================================================================================================================
//	public static void InitializePatientAge()
//	{		
//		for(int i = 0; i < 5; i++)
//		{
//			pAge[i] = (int)(Math.random()* 100 + 1);
//		}//end for(int i = 0; i < 5; i++)
//		JOptionPane.showMessageDialog(null, "Randomized, initial patient ages: " 
//											+ pAge[0] + ", " 
//											+ pAge[1] + ", " 
//											+ pAge[2] + ", "  
//											+ pAge[3] + ", " 
//											+ pAge[4]);
//	}//end public static void InitializePatientAge()
//=========================================================================================================================================================================================================================
	public static Integer DisplayMenu(int menuSelection)
	{
		System.out.println();
		input = JOptionPane.showInputDialog(null, "Welcome to GlucoWatch!" +
													"\n1- Add a Patient" +
													"\n2- Display all Patients" +
													"\n3- Search for a Patient" +
													"\n4- Exit the program");
		menuSelection = Integer.parseInt(input);
		ValidateMenuSelection(menuSelection);
		return menuSelection;
	}//public static void DisplayMenu()
//=========================================================================================================================================================================================================================
	public static int ValidateMenuSelection(int menuSelection)
	{
		while(menuSelection < 0 || menuSelection > 4)
		{
			input = JOptionPane.showInputDialog(null, "Invalid input! Select one of the following options: "+
														"\n1- Add a Patient" +
														"\n2- Display all Patients" +
														"\n3- Search for a Patient" +
														"\n4- Exit the program");
			menuSelection = Integer.parseInt(input);
		}//end while(menuSelection < 0 || menuSelection > 4)
		return menuSelection;
	}//end public static int ValidateMenuSelection()
//=========================================================================================================================================================================================================================

//=========================================================================================================================================================================================================================	
	public static void DisplayFastingReq()
	{
		JOptionPane.showMessageDialog(null, "Patients must fast for 12 hours prior to having their glucose readings taken!");
	}//end public static void DisplayFastingReq()
//=========================================================================================================================================================================================================================	
	public static Integer GetContinue(int start)
	{
		input = JOptionPane.showInputDialog("Would you like to enter another patient's reading?" +
				"\n1 - Yes" +
				"\n0 - No");
		start = Integer.parseInt(input);
		ValidateStart(start);
		return start;
	}//end public static Integer GetContinue(int start)
//=========================================================================================================================================================================================================================	
	public static int FindPatient(int patientFound)
	{
		input = JOptionPane.showInputDialog(null, "Enter the patient's 9 digit social: ");
		for(int i = 0; i < pNum; i++)
		{
			if(input.equals(pList.get(i).pSSN))
			{
				patientFound++;
				pFoundAt = i;
			}//end if(input.equals(pSSN[i]))
		}//end for(int i = 0; i < pNum; i++)
		return patientFound;
	}//end public static int FindPatient(int patientFound)
//=========================================================================================================================================================================================================================	
	public static void DisplayFinalReports()
	{
		//declarations
		int foundAt = 0;
		double comparison = 0.0;
		
		//find lowest glucose level patient
		for(int i = 0; i < totalPatients; i++)
		{
			if (comparison == 0)
			{
				comparison = pList.get(i).pGReading;
			}//end if (comparison < pGReading)
			else
			{
				if(pList.get(foundAt).pGReading < comparison)
				{
					comparison = pList.get(i).pGReading;
					foundAt = i;
				}//end 
			}//end else
		}//end (int i = 0; i < totalPatients; i++)
		
		JOptionPane.showMessageDialog(null, "Final Reports: Lowest Glucose Level Patient " +
											"\nPatient: " + pList.get(foundAt).pName +
											"\nSSN: " + pList.get(foundAt).pSSN +
											"\nAge: " + pList.get(foundAt).pAge +
											"\nFasting Status: " + pList.get(foundAt).pFStatus +
											"\nGlucose Reading: " + DF.format(pList.get(foundAt).pGReading));
		
		comparison = 0;
		
		//find highest glucose level patient
		for(int i = 0; i < totalPatients; i++)
		{
			if (pList.get(i).pGReading > comparison)
			{
				comparison = pList.get(i).pGReading;
				foundAt = i;
			}//end if (comparison < pGReading)
		}//end (int i = 0; i < totalPatients; i++)
		
		JOptionPane.showMessageDialog(null, "Final Reports: Highest Glucose Level Patient " +
											"\nPatient: " + pList.get(foundAt).pName +
											"\nSSN: " + pList.get(foundAt).pSSN +
											"\nAge: " + pList.get(foundAt).pAge +
											"\nFasting Status: " + pList.get(foundAt).pFStatus +
											"\nGlucose Reading: " + DF.format(pList.get(foundAt).pGReading));
		
		if(hLPatients > nLPatients && hLPatients > lLPatients)
		{
			domLvl = "High Glucose Level Patients";
		}//end if(hLPatients > nLPatients)
		else if(nLPatients > lLPatients && nLPatients > hLPatients)
		{
			domLvl = "Normal Glucose Level Patients";
		}//end if(nLPatients > lLPatients)
		else if(lLPatients > nLPatients && lLPatients > hLPatients)
		{
			domLvl = "Low Glucose Level Patients";
		}//end if(lLPatients > nLPatients && lLPatients > hLPatients)
		else
		{
			domLvl = "Equal Patient Levels";
		}//end else
		
		JOptionPane.showMessageDialog(null, "Total Patient Data: " +
											"\nTotal of all patients processed: " + DF.format(totalPatients) +
											"\nHigh Level : Normal Level : Low Level Patient Ratio " + DF.format(hLPatients) +" : " + DF.format(nLPatients) + " : " + DF.format(lLPatients) +
											"\nDominant Level: " + domLvl +
											"\nHigh Level Percentage: " + DF.format(((hLPatients/totalPatients)*100)) + "%" +
											"\nNormal Level Percentage: " + DF.format(((nLPatients/totalPatients)*100)) + "%" +
											"\nLow Level Percentage: " + DF.format(((lLPatients/totalPatients)*100)) + "%");
	}//end public static void DisplayFinalReports()
//=========================================================================================================================================================================================================================	
	public static void DisplayGoodbye()
	{
		JOptionPane.showMessageDialog(null, "Thank you for using GlucoWatch. Goodbye!");
	}//end public static void DisplayGoodbye()
//=========================================================================================================================================================================================================================	
}//end public class HW2_Yousef_MOD_GUI_OOPv2
