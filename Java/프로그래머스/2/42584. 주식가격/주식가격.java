import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<int[]> price = new Stack<>();
        // Stack<int[]> temp = new Stack<>();
        for(int i=0; i<prices.length; i++) {
            while(!price.empty() && price.peek()[1] > prices[i]) {
                if(price.peek()[1] > prices[i]) {
                    answer[price.peek()[0]] += i-price.peek()[0];
                    price.pop();
                }
            }
            price.push(new int[]{i, prices[i]});
        }
        while(!price.empty()) {
            answer[price.peek()[0]] += prices.length-price.peek()[0]-1;
            price.pop();
        }
            
        //     while(!price.empty() && price.peek()[1] > prices[i]) {
        //         answer[price.peek()[0]]++;
        //         price.pop();
        //     }
        //     while(!price.empty()) {
        //         temp.push(price.pop());
        //         answer[temp.peek()[0]]++;
        //     }
        //     while(!temp.empty()) {
        //         price.push(temp.pop());
        //     }
        //     price.push(new int[]{i, prices[i]});
        
        return answer;
    }
}