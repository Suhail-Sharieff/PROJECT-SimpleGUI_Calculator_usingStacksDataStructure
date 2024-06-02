import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;
import java.awt.*;

/**
 * calculator
 */

class TextArea extends JTextField {
    TextArea() {
        this.setPreferredSize(new Dimension(400, 50));
        this.setBackground(Color.green);
        this.setForeground(Color.red);
        this.setFont(new Font("MV Boli", Font.PLAIN, 20));
        this.setBorder(BorderFactory.createTitledBorder("INPUT"));
    }
}

class NumGrid extends JPanel implements ActionListener {
    JButton btn[] = {
        new JButton("0"), new JButton("1"), new JButton("2"), new JButton("3"),
        new JButton("4"), new JButton("5"), new JButton("6"), new JButton("7"),
        new JButton("8"), new JButton("9"), new JButton("+"), new JButton("-"),
        new JButton("*"), new JButton("/"), new JButton("("), new JButton("."),
        new JButton(")"), new JButton("CLEAR"), new JButton("=")
    };

    private TextArea textArea;
    private AnsPanel ansPanel;
    private StringBuilder sb = new StringBuilder();

    NumGrid(TextArea textArea, AnsPanel ansPanel) {
        this.textArea = textArea;
        this.ansPanel = ansPanel;
        for (JButton jButton : btn) {
            JButton curr = jButton;
            
            curr.setFocusable(false);
            curr.setFont(new Font("Verdana", Font.PLAIN, 20));
            curr.addActionListener(this);
            this.add(curr);
        }

        this.setLayout(new GridLayout(5, 4));
        this.setPreferredSize(new Dimension(400, 300));
        this.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton curr = (JButton) e.getSource();
        String input = curr.getText();

        if (input.equals("CLEAR")) {
            sb.setLength(0);
            textArea.setText("");
            ansPanel.setAnswer("");
        } else if (input.equals("=")) {
            String expression = sb.toString();
            try {
                float result = evaluate(postfixFormOf(expression));
                textArea.setText(expression );
                ansPanel.setAnswer(String.valueOf(result));
            } catch (Exception ex) {
                textArea.setText("Error");
                ansPanel.setAnswer("Error");
            }
        } else {
            sb.append(input);
            textArea.setText(sb.toString());
        }
    }

    public static String postfixFormOf(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                sb.append(ch);
            } else if (ch == '(') {
                st.push(ch);
            } else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    sb.append(' ');
                    sb.append(st.pop());
                }
                st.pop();
            } else {
                sb.append(' ');
                while (!st.isEmpty() && precedence(ch) <= precedence(st.peek())) {
                    sb.append(st.pop());
                    sb.append(' ');
                }
                st.push(ch);
            }
        }
        while (!st.isEmpty()) {
            sb.append(' ');
            sb.append(st.pop());
        }
        return sb.toString();
    }

    public static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    public static float evaluate(String postfixedString) {
        Stack<Float> st = new Stack<>();
        String[] tokens = postfixedString.split(" ");
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            char ch = token.charAt(0);
            if (Character.isDigit(ch) || token.length() > 1) {
                st.push(Float.parseFloat(token));
            } else {
                float num2 = st.pop();
                float num1 = st.pop();
                switch (ch) {
                    case '+':
                        st.push(num1 + num2);
                        break;
                    case '-':
                        st.push(num1 - num2);
                        break;
                    case '*':
                        st.push(num1 * num2);
                        break;
                    case '/':
                        st.push(num1 / num2);
                        break;
                }
            }
        }
        return st.pop();
    }
}

class AnsPanel extends JPanel {
    JTextField ans = new JTextField();

    AnsPanel() {
        ans.setBorder(BorderFactory.createTitledBorder("ANSWER"));
        ans.setPreferredSize(new Dimension(400, 50));
        ans.setBackground(Color.pink);
        ans.setForeground(Color.blue);
        ans.setFont(new Font("MV Boli", Font.PLAIN, 20));
        ans.setEditable(false);
        this.add(ans);
    }

    public void setAnswer(String answer) {
        ans.setText(answer);
    }
}

public class calculator extends JFrame {
    TextArea textArea = new TextArea();
    AnsPanel ansPanel = new AnsPanel();
    NumGrid grid = new NumGrid(textArea, ansPanel);

    calculator() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.add(textArea);
        this.add(grid);
        this.add(ansPanel);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new calculator();
    }
}
