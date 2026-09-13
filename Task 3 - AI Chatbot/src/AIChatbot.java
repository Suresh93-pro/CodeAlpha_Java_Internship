import javax.swing.*;import java.awt.*;
public class AIChatbot extends JFrame{
 JTextArea chat=new JTextArea();JTextField input=UI.input("Type your message...");ChatbotEngine bot=new ChatbotEngine();
 public AIChatbot(){setTitle("CodeAlpha • AI Chatbot");setSize(760,600);setLocationRelativeTo(null);UI.style(this);
  JPanel head=UI.card();head.setLayout(new BorderLayout());head.add(UI.title("CODEALPHA AI CHATBOT"),BorderLayout.WEST);head.add(UI.muted("Rule-based NLP • FAQ engine"),BorderLayout.EAST);
  chat.setEditable(false);chat.setLineWrap(true);chat.setWrapStyleWord(true);chat.setBackground(new Color(7,15,29));chat.setForeground(UI.TEXT);chat.setFont(new Font("Segoe UI",0,15));chat.setText("Assistant: Hello! Ask me about Java, OOP, grades, stocks, hotels or the internship.\\n\\n");
  JPanel bottom=UI.card();JButton send=UI.button("Send");JButton clear=UI.button("Clear");bottom.add(input);bottom.add(send);bottom.add(clear);
  send.addActionListener(e->send());input.addActionListener(e->send());clear.addActionListener(e->chat.setText(""));
  setLayout(new BorderLayout(14,14));add(head,BorderLayout.NORTH);add(new JScrollPane(chat),BorderLayout.CENTER);add(bottom,BorderLayout.SOUTH);
 }
 void send(){String q=input.getText().trim();if(q.isEmpty())return;chat.append("You: "+q+"\\nAssistant: "+bot.reply(q)+"\\n\\n");input.setText("");chat.setCaretPosition(chat.getDocument().getLength());}
 public static void main(String[]a){SwingUtilities.invokeLater(()->new AIChatbot().setVisible(true));}
}
