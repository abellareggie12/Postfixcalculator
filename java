import java.util.*;

public class PostfixCalculator{

   private String expression;
   private Stack<String> stack = new Stack<String>();
  
   public String[] tokenize(){
      String [] tokens=null;
      if(expression != null){
         tokens = expression.split(" ");
      }
      return tokens;
   }
  
   public boolean isOperand(String str){
      try{
         Integer.parseInt(str);
      }
      catch(Exception e){
         return false;
      }
      return true;
   }
  
   public int calculate(){
   //do the calculation here
      String[] item = tokenize();
      Stack<String> newStack = new Stack<String>();
      
      if(expression != null) {
         for(int x=0; x<item.length; x++) {
            if(isOperand(item[x])) {
               newStack.push(item[x]);          
            } else {
               int b = Integer.parseInt(newStack.pop());
               int a = Integer.parseInt(newStack.pop());
               
               if(item[x].equals("*")) {
                  newStack.push(Integer.toString(a*b)); 
               } else if(item[x].equals("/")) {
                  newStack.push(Integer.toString(a/b));
               } else if(item[x].equals("+")) {
                  newStack.push(Integer.toString(a+b));
               } else {
                  newStack.push(Integer.toString(a-b)); 
               }
            }
         }
      }
      return Integer.parseInt(newStack.pop());
   }
  
  public String[] convertToPostfix(){
  //convert expression to postfix
   int i = 0;
   String[] item = tokenize();
   String[] postfix = new String[item.length];
   Stack<String> newStack = new Stack<String>();
   
   if(expression != null) {
      for(int x=0; x<item.length; x++) {
         // OPERATOR
         if(isOperator(item[x])) { 
            if(item[x].equals("*") || item[x].equals("/")) {
               if(newStack.isEmpty()) {
                  newStack.push(item[x]);
               } else {
                  while(!newStack.isEmpty()) {
                     if(newStack.peek().equals("*") || newStack.peek().equals("/")) {
                        postfix[i] = newStack.pop(); i++;  
                        
                        if(newStack.isEmpty()) {
                           newStack.push(item[x]);
                           break;
                        }
                     } else {
                        newStack.push(item[x]);
                        break;
                     }
                  } 
               }
            } else {
               if(newStack.isEmpty()) {
                  newStack.push(item[x]);
               } else {
                  while(!newStack.isEmpty()) {
                     if(!newStack.peek().equals("(")) {
                        postfix[i] = newStack.pop(); i++;  
                        
                        if(newStack.isEmpty()) {
                           newStack.push(item[x]);
                           break;
                        }
                     } else {
                        newStack.push(item[x]);
                        break;
                     }
                  } 
               }
            }
         // PARENTHESIS
         } else if(isParenthesis(item[x])) { 
            if(item[x].equals("(")) {
               newStack.push(item[x]);
            } else {
               while(!newStack.isEmpty())
               if(!newStack.peek().equals("(")) {
                  postfix[i] = newStack.pop(); i++;
               } else {
                  newStack.pop();
                  break;
               }
            }
         // NUMBERS
         } else { 
            postfix[i] = item[x];
            i++;
         }
      }

      while(!newStack.isEmpty()) {
         if(postfix[i] != null) {
            postfix[i] = newStack.pop();
            i++;
         } else i--;
      }
   }
   
   for(int x=0; x<item.length; x++) {
      if(postfix[x] != null)
         System.out.print(postfix[x] + " ");    
   }
      return postfix;
  }
  
  public boolean isOperator(String op) {
   switch(op) {
      case "*": return true;
      case "/": return true;
      case "+": return true;
      case "-": return true;
      default: return false;
   }
  }
  
  public boolean isParenthesis(String op) {
   switch(op) {
      case ")": return true;
      case "(": return true;
      default: return false;
   }
  }

   public void pushing(){
      String tokens [] = tokenize();
      if(tokens != null){
         for(String token:tokens)
            stack.push(token);
      }
   }
  
   public void displayElements(){
      if(expression != null){
         while(!stack.isEmpty()){
            String item = stack.pop();
            System.out.println(item + "\t" + (isOperand(item)?"operand":"not operand"));
         }
      }
   }
  
   public static void main(String [] args){
      PostfixCalculator calc = new PostfixCalculator();
      Scanner input = new Scanner(System.in);
      
      System.out.print("Input infix expression: ");
      //calc.expression = "12 25 5 1 / / * 8 7 + -";
      calc.expression = input.nextLine();
      calc.convertToPostfix();
      //System.out.print(calc.calculate());
      //calc.pushing();
      //
      //calc.displayElements();
   
   }
}
