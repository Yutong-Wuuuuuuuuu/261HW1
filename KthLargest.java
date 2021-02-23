/**
 * Author: Yutong Wu
 * Email: yw6228@rit.edu
 * Finds the kth largest element in an unsorted array
 */
public class KthLargest {

    /**
     * This method finds the kth largest element in an array
     * @param a Unsorted array given
     * @param k how large the element the user wants it to be
     * @return The Kth largest element in array a
     */
    public static int kthLargest(int[] a, int k){
        int[] firstK = new int[k];
        for(int i = 0; i < k; i++){

            int currIdx = i - 1;
            while(currIdx >= 0 && firstK[currIdx] > a[i]){
                firstK[currIdx + 1] = firstK[currIdx];
                currIdx --;
            }
            firstK[currIdx + 1] = a[i];
        }
        for(int j = k; j < a.length; j++){
            if(firstK[0] < a[j]){
                firstK[0] = a[j];
                int currIdx = 1;
                while(currIdx < k && firstK[currIdx] < a[j]){
                    firstK[currIdx - 1] = firstK[currIdx];
                    currIdx ++;
                }
                firstK[currIdx - 1] = a[j];
            }
        }

        return firstK[0];
    }


}
