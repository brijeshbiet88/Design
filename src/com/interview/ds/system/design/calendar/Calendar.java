package com.interview.ds.system.design.calendar;

import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Year : ");
		int year = sc.nextInt();
		generateCalendar(year);
	}

	//base year 2000,  Jan 1  it was Saturday i.e. 6th day of month
	private static void generateCalendar(int year) {
		int baseYear = 2000, baseDay = 6;

		int firstDay = calculateFirstDayOfYear(baseYear, baseDay, year);

		for (int i = 1; i <= 12; i++) {
			int nofDays = daysInMonth(year, i);
			printCalendarForMonth(year , i , firstDay , nofDays);
			System.out.println("\n");
			firstDay = (firstDay + nofDays ) % 7;
		}
	}

	private static void printCalendarForMonth(int year, int month, int firstDay , int noOfDays) {
		System.out.println("       Year "+year+", "+MonthName.values()[month-1]+"        ");
		System.out.println("S    M    T    W    T    F    S");
		for(int i = 0 ; i < firstDay ; i++)
			System.out.print("     ");
		
		for(int i = 1 ; i <= noOfDays ; i++) {
			if(i < 10)
			System.out.print(i+"    ");
			else
				System.out.print(i+"   ");
			if((i + firstDay) % 7 == 0) {
				System.out.println();
			}
		}
	}

	/* can be optimized more */
	private static int calculateFirstDayOfYear(int baseYear, int baseDay, int year) {
		int yearDiff ;
		if(year > baseYear) {
			yearDiff = (year - baseYear) % 400;
			
			while(yearDiff > 100) {
				yearDiff = yearDiff - 100;
				baseDay = (baseDay + 5) % 7;
						
			}
			
			for (int i = baseYear ; i < (baseYear + yearDiff) ; i++) {
				 if(isLeapYear(i)) {
					 baseDay = baseDay + 2;
				 }
				 else {
					 baseDay++;
				 }
			}
			
		}
		
		else if(year < baseYear) {
			yearDiff = (baseYear - year) % 400;
			while(yearDiff > 100) {
				yearDiff = yearDiff - 100;
				baseDay = (baseDay + 2) % 7;
						
			}
			
			while(yearDiff > 4) {
				yearDiff = yearDiff - 4;
				baseDay = (baseDay + 2) % 7;
						
			}
			
			for (int i = baseYear -1 ; i >= (baseYear - yearDiff) ; i--) {
				 if(isLeapYear(i)) {
					 baseDay = baseDay - 2;
				 }
				 else {
					 baseDay--;
				 }
			}
			if(baseDay < 0) {
				baseDay = 7 + baseDay % 7;
			}
		}
		
		return baseDay % 7;
	}
	
	private static boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0) {
			return true;
		}

		else if (year % 4 == 0 && year % 100 == 0) {

			if (year % 400 == 0)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	private static int daysInMonth(int year, int month) {
		if (month < 1 && month > 12) {
			System.out.println("Not A Valid Month ");
			return -1;
		}

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			return 31;

		if (month == 4 || month == 6 || month == 9 || month == 11)
			return 30;

		if (month == 2 && isLeapYear(year))
			return 29;
		else
			return 28;
	}
}

/*
 Enter Year : 2020
       Year 2020, JAN        
S    M    T    W    T    F    S
               1    2    3    4    
5    6    7    8    9    10   11   
12   13   14   15   16   17   18   
19   20   21   22   23   24   25   
26   27   28   29   30   31   

       Year 2020, FEB        
S    M    T    W    T    F    S
                              1    
2    3    4    5    6    7    8    
9    10   11   12   13   14   15   
16   17   18   19   20   21   22   
23   24   25   26   27   28   29   


       Year 2020, MAR        
S    M    T    W    T    F    S
1    2    3    4    5    6    7    
8    9    10   11   12   13   14   
15   16   17   18   19   20   21   
22   23   24   25   26   27   28   
29   30   31   

       Year 2020, APR        
S    M    T    W    T    F    S
               1    2    3    4    
5    6    7    8    9    10   11   
12   13   14   15   16   17   18   
19   20   21   22   23   24   25   
26   27   28   29   30   

       Year 2020, MAY        
S    M    T    W    T    F    S
                         1    2    
3    4    5    6    7    8    9    
10   11   12   13   14   15   16   
17   18   19   20   21   22   23   
24   25   26   27   28   29   30   
31   

       Year 2020, JUN        
S    M    T    W    T    F    S
     1    2    3    4    5    6    
7    8    9    10   11   12   13   
14   15   16   17   18   19   20   
21   22   23   24   25   26   27   
28   29   30   

       Year 2020, JUL        
S    M    T    W    T    F    S
               1    2    3    4    
5    6    7    8    9    10   11   
12   13   14   15   16   17   18   
19   20   21   22   23   24   25   
26   27   28   29   30   31   

       Year 2020, AUG        
S    M    T    W    T    F    S
                              1    
2    3    4    5    6    7    8    
9    10   11   12   13   14   15   
16   17   18   19   20   21   22   
23   24   25   26   27   28   29   
30   31   

       Year 2020, SEP        
S    M    T    W    T    F    S
          1    2    3    4    5    
6    7    8    9    10   11   12   
13   14   15   16   17   18   19   
20   21   22   23   24   25   26   
27   28   29   30   

       Year 2020, OCT        
S    M    T    W    T    F    S
                    1    2    3    
4    5    6    7    8    9    10   
11   12   13   14   15   16   17   
18   19   20   21   22   23   24   
25   26   27   28   29   30   31   


       Year 2020, NOV        
S    M    T    W    T    F    S
1    2    3    4    5    6    7    
8    9    10   11   12   13   14   
15   16   17   18   19   20   21   
22   23   24   25   26   27   28   
29   30   

       Year 2020, DEC        
S    M    T    W    T    F    S
          1    2    3    4    5    
6    7    8    9    10   11   12   
13   14   15   16   17   18   19   
20   21   22   23   24   25   26   
27   28   29   30   31   


 */
