
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int width;
        int length;
        int capacity = 0;
        int max = 0;

        while (left < right) {
            width = right - left;

            if (height[left] > height[right]) {
                length = height[right];
            } else {
                length = height[left];
            }

            capacity = width * length;
            if(max < capacity){
                max = capacity;
            }

            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return max;
    }
}
