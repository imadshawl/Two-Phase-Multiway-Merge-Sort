import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


//Two Phase Multiway Merge Sort

public class TPMMS {
    //sorting each sublist of size = RAM_Size
    //Phase 1
    static int[] sort(String data) {
        String[] str = data.split("\\s+");
        StringBuilder sb = new StringBuilder();
        int[] integers = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            integers[i] = Integer.parseInt(str[i]);
        }
        //Arrays.sort uses dual-pivot Quicksort with run time complexity = O(nlog(n))
        Arrays.sort(integers);
        for (int i = 0; i < integers.length; i++) {
            sb.append(integers[i]).append(" ");
        }

        System.out.println(sb);
        System.out.println();
        System.out.println("------------");
        return integers;
    }


    //Creating N/S sublists and displaying them before and after sorting
    static void create_sortedsublists(int RAM_size) {
        try {
            String path = "data.txt";
            File f = new File(path);
            Scanner s = new Scanner(f);
            String l = "";
            ArrayList<int[]> initial_list = new ArrayList<>();
            ArrayList<int[]> final_list = new ArrayList<>();

            System.out.println("***** PHASE 1 *****");
            System.out.println();
            System.out.println("Here are N/S blocks of data before and after sorting");
            System.out.println("N : Total number of integers");
            System.out.println("S : Size of our RAM");
            System.out.println();

            while (s.hasNextLine()) {
                for (int i = 0; i < RAM_size; i++) {
                    if (s.hasNextLine()) {
                        String d1 = s.nextLine();
                        l = l.concat(d1 + " ");
                    } else {
                        break;
                    }
                }

                System.out.println(l);
                initial_list.add(sort(l));
                l = "";
            }
            s.close();
            System.out.println("Phase 1 Done !");
            System.out.println("Sorted SubLists after phase 1 : ");
            for(int i = 0; i < initial_list.size(); i++)
            {
                int[] currArr = initial_list.get(i);

                System.out.println("Array at index " + i + " is: " + Arrays.toString(currArr));
            }


            //End of phase 1, begin phase 2
            //Initialize a counter for number of passes
            int num_passes = 1;
            boolean first_pass = true;
            while(final_list.size() != 1) {
                System.out.println("***** PHASE 2 *****");
                System.out.println("Pass : " + num_passes);
                System.out.println("--------");
                num_passes++;
                if (!first_pass) {
                    initial_list = (ArrayList<int[]>) final_list.clone();
                    final_list.clear();
                }
                first_pass = false;
                while (!initial_list.isEmpty()) {
                    ArrayList<int[]> merging_lst = new ArrayList<>();
                    for (int i = 0; i < RAM_size; i++) {
                        if (!initial_list.isEmpty()) {
                            merging_lst.add(initial_list.remove(0));
                        } else {
                            break;
                        }
                    }
                    int[] sc = chunk(merging_lst);
                    final_list.add(sc);
                    System.out.println("-------------");
                }
            }
            for(int i = 0; i < final_list.size(); i++)
            {
                int[] currArr = final_list.get(i);

                System.out.println("Final List after sorting is: " + Arrays.toString(currArr));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    //Merge Sort Algo
    static int [] mergeSort(int arr[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merger(arr, l, m, r);
        }
        return arr;
    }

    //Phase 2
    static int[] chunk(ArrayList<int[]>c) {
        int[][] array_chunks = new int[c.size()][c.size()];
        array_chunks = c.toArray(array_chunks);

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < array_chunks.length; i++) {
            for(int v:array_chunks[i]) {
                arrayList.add(v);
            }
        }
        int a[] = new int[arrayList.size()];
        int x=0;
        for(int y: arrayList) {
            a[x] = y;
            x++;
        }
        //calling mergesort to join the sorted sublists as part of phase 2
        //keep returning sorted runs until no more runs are left.
        int done[] = mergeSort(a, 0, a.length-1);
        for(int v: done){
            System.out.println(v + " ");
        }
        System.out.println();
        return done;
    }

    //merge for phase 2 based on RAM size, number of passes may be more than 1
    static void merger(int arr[], int x, int y, int z){
        int a1 = y - x + 1;
        int a2 = z - y;
        int R[] = new int[a1];
        int S[] = new int[a2];

        for (int i = 0; i < a1; i++) {
            R[i] = arr[x+i];
        }
        for (int j = 0; j < a2; j++) {
            S[j] = arr[y+j+1];
        }
        int i=0;
        int j=0;
        int k=x;

        //compare values and select the smaller value
        while (i<a1 && j<a2){
            if(R[i] <= S[j]) {
                arr[k] = R[i];
                i++;
            } else {
                arr[k] = S[j];
                j++;
            }
            k++;
        }
        while (i<a1){
            arr[k] = R[i];
            i++;
            k++;
        }
        while (j<a2){
            arr[k] = S[j];
            j++;
            k++;
        }
    }


}
