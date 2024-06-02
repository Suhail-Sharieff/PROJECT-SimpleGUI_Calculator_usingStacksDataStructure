
![Screenshot (22)](https://github.com/Suhail-Sharieff/PROJECT-SimpleGUI_Calculator_usingStacksDataStructure/assets/149879419/93c5e045-5778-4869-b20b-b8740912e80f)






Simple Calculator in Java Swing
This project is a simple calculator application built using Java Swing. The calculator can handle basic arithmetic operations such as addition, subtraction, multiplication, and division. It supports input through a graphical user interface with buttons for digits, operators, and parentheses.

Features
User Interface:

Input area for displaying the current expression.
Grid layout for numeric and operator buttons.
Separate panel for displaying the result.
Functionality:

Supports basic arithmetic operations: addition (+), subtraction (-), multiplication (*), and division (/).
Handles parentheses for operation precedence.
"CLEAR" button to reset the input area.
"=" button to evaluate the expression and display the result.
Layout:

Uses FlowLayout for the main frame.
Components are arranged to fit within a 500x500 window.
Font and component sizes are adjusted to ensure readability and proper fitting within the specified window dimensions.
Technical Details
Classes:

TextArea: A customized JTextField for displaying the input expression.
NumGrid: A JPanel containing buttons for digits and operators, handling input actions.
AnsPanel: A JPanel for displaying the evaluated result.
Calculator: The main JFrame class that assembles the TextArea, NumGrid, and AnsPanel.
Evaluation Logic:

Converts the infix expression to postfix notation using the Shunting Yard algorithm.
Evaluates the postfix expression to compute the result.
