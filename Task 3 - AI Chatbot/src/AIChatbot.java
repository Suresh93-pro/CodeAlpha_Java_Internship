import javax.swing.*;
import java.awt.*;

public class AIChatbot extends JFrame {

    JTextArea chat = new JTextArea();
    JTextField input = UI.input("Type your message...");
    ChatbotEngine bot = new ChatbotEngine();

    public AIChatbot() {

        setTitle("CodeAlpha • AI Chatbot");
        setSize(900, 650);
        setLocationRelativeTo(null);
        UI.style(this);

        // Header
        JPanel head = UI.card();
        head.setLayout(new BorderLayout());

        head.add(
            UI.title("CODEALPHA AI CHATBOT"),
            BorderLayout.WEST
        );

        head.add(
            UI.muted("Rule-based NLP • FAQ engine"),
            BorderLayout.EAST
        );

        // Chat area
        chat.setEditable(false);
        chat.setLineWrap(true);
        chat.setWrapStyleWord(true);

        chat.setBackground(new Color(7, 15, 29));
        chat.setForeground(UI.TEXT);
        chat.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        chat.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        chat.setText(
            "Assistant: Hello! Ask me about Java, OOP, grades, stocks, hotels or the internship.\n\n"
        );

        // Input area
        JPanel bottom = UI.card();
        bottom.setLayout(new BorderLayout(10, 10));

        input.setPreferredSize(new Dimension(600, 45));
        input.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JButton send = UI.button("Send");
        JButton clear = UI.button("Clear");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        buttons.setOpaque(false);

        buttons.add(send);
        buttons.add(clear);

        bottom.add(input, BorderLayout.CENTER);
        bottom.add(buttons, BorderLayout.EAST);

        // Actions
        send.addActionListener(e -> send());

        input.addActionListener(e -> send());

        clear.addActionListener(
            e -> chat.setText(
                "Assistant: Chat cleared. How can I help you?\n\n"
            )
        );

        // Window layout
        setLayout(new BorderLayout(14, 14));

        add(head, BorderLayout.NORTH);
        add(new JScrollPane(chat), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    // Send message
    void send() {

        String q = input.getText().trim();

        if (q.isEmpty()) {
            return;
        }

        chat.append(
            "You: " + q
            + "\n"
            + "Assistant: " + bot.reply(q)
            + "\n\n"
        );

        input.setText("");

        chat.setCaretPosition(
            chat.getDocument().getLength()
        );
    }

    public static void main(String[] a) {

        SwingUtilities.invokeLater(
            () -> new AIChatbot().setVisible(true)
        );
    }
}