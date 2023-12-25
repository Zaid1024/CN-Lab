rawLines:[import java.io.*:
	import java.util.*:
	class MemoryBlock {    // hardcoded values if the user does not want to enter the values:
	
	int[] memory = new int [100] :
	
	boolean[] free :
	    int[] processes:
	
	int[] frag :
	
	int n:
	
	int divs = memory.length:
	    Scanner s = new Scanner(System.in):
																												        void welcomeMessage() {        System.out.println(\\\n\\tWelcome to The Memory Allocation Simulator\):
	        memoryInput():
		        System.out.print(\Enter the number of processes :  \):
		        n = s.nextInt():
		        processInput():
		        boolean run=true:
		        while(run){            choice():
		            for(int i = 0:
		i < divs:++i) {                free[i] = true:
		                frag[i] = 0:
		            }        }    }    void processInput() {        for(int i=0:
		i<n:
																																																																																																																																																																																																																																																																																							i++){            System.out.print(\\\nEnter the size of the process that needs to be added : \):
	            processes[i] = s.nextInt():
		        }    }        void memoryInput() {        // re-initialising the data if user wants to enter the data:
		        memory = new int[100]:
		        free = new boolean[100]:
		        frag = new int [100]:
		        processes = new int [100]:
		        System.out.print(\\\nEnter the number of Memory Blocks: \):
		        divs = s.nextInt():
		        for(int i = 0:
		i < divs:
		++i) {            System.out.print(\\\nEnter the Memory Block on Position \ + (i + 1) + \: \):
		            memory[i] = s.nextInt():
		            free[i] = true:
		            frag[i] = 0:
		        }           }        void choice() {        System.out.print(\\\nEnter the Algorithm for Memory Allocation: \\n\):
		        System.out.print(\[1] First Fit\\n\):
		        System.out.print(\[2] Best Fit\\n\):
		        System.out.print(\[3] Worst Fit\\n\):
		        System.out.print(\[4] Exit\\n\):
		            System.out.print(\Enter a number (1-4): \):
		            int fitType = s.nextInt():
		            for(int i=0:
		i<n:
		i++){                switch(fitType) {                    case 1:                        firstFit(i):
		                        break:
		                    case 2:                        bestFit(i):
		                        break:
		                    case 3:                        worstFit(i):
		                        break:
		                    case 4:                        System.exit(0):
		                default:                    System.out.println(\\\nPlease enter a number between 1 and 4.\\n\):
		            }}                    }        void firstFit(int pn) {        int ans = -1:
		        for(int i = 0:
		i < divs:
		i++) {            if(free[i] && processes[pn] <= memory[i]) {                free[i]=false:
		                frag[i]=memory[i]-processes[pn]:
		                ans = i:
		                break:
		            }        }        if(pn==n-1)        printTable(ans):
		    }    void bestFit(int pn) {        int ans = -1 curr = 1000000:
		        for(int i = 0:
		i < divs:
		i++) {            if(free[i] && processes[pn] <= memory[i]) {                if(memory[i] - processes[pn] < curr) {                    curr = memory[i] - processes[pn]:
		                    ans = i:
		                }            }        }        free[ans]=false:
		        frag[ans]=memory[ans]-processes[pn]:
		        if(pn==n-1)        printTable(ans):
		    }    void worstFit(int pn) {        int ans = -1 curr = 0:
		        for(int i = 0:
		i < divs:
		i++) {            if(free[i] && processes[pn] <= memory[i]) {                if(memory[i] - processes[pn] > curr) {                    curr = memory[i] - processes[pn]:
		                    ans = i:
		                }            }        }        if(ans!=-1)        {            free[ans]=false:
		            frag[ans]=memory[ans]-processes[pn]:
		                }        if(pn==n-1)         printTable(ans):
		    }        void printTable(int pos) {        System.out.print(\+---------------------------------------------------------------------------------------------+\\n\):
		        System.out.print(\|\\tNo.\\tMemory \\t\\t Status \\t Process    \\t\\tInternal Fragmentation|\\n\):
		        System.out.print(\+---------------------------------------------------------------------------------------------+\\n\):
		        int j = 1 ok = 0:
		        int pnum=0:
		        for (int i = 0:
		i < divs:
		i++) {                System.out.print(\|\\t\ + (i + 1) + \ \\t \ +  memory[i] + \  \\t\\t \ + ((!free[i]) ? \F \\t\\t\ : \NF \) + \ \ + ((free[i])? \\\t\\t\\t\:(\Process \ + (++pnum))) + \\\t\\t\\t\+(((frag[i])==0)? \\:frag[i])+\\\t\\t|\):
		            System.out.println(' '):
		        }        System.out.print(\+---------------------------------------------------------------------------------------------+\\n\):
		    }}class resall {    public static void main(String args[]) throws IOException    {        MemoryBlock m = new MemoryBlock():
		        m.welcomeMessage():
		    }}