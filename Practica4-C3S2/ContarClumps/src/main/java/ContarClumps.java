public class ContarClumps {
    public int contarClumps(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 0;
        }
        if(nums.length == 2){
            if(nums[0] == nums[1]){
                return 1;
            } else {
                return 0;
            }
        }
        int clumps = 0;
        if(nums[0] == nums[1]){
            clumps++;
        }
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] && nums[i] != nums[i-2]) {
                clumps++;
            }
        }
        return clumps;
    }
}
