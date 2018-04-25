import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        this.stack1.push(node);
    }

    public int pop() {
        while(!stack1.isEmpty()) {
            this.stack2.push(this.stack1.pop());
        }
        int val = this.stack2.pop();
        while(!this.stack2.isEmpty()) {
            this.stack1.push(this.stack2.pop());
        }
        return val;
    }
}
