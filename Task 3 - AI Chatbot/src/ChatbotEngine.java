import java.util.*;

public class ChatbotEngine {

    public String reply(String q) {

        String x = q.toLowerCase(Locale.ROOT).trim();

        if (x.matches(".*\\b(hi|hello|hey)\\b.*")) {
            return "Hello! I am CodeAlpha AI Assistant. How can I help you?";
        }

        if (x.contains("java")) {
            return "Java is an object-oriented, platform-independent programming language. "
                 + "It uses classes, objects, inheritance, polymorphism, abstraction and encapsulation.";
        }

        if (x.contains("oops") || x.contains("oop")) {
            return "The four pillars are Encapsulation, Inheritance, Polymorphism and Abstraction.";
        }

        if (x.contains("internship")) {
            return "This chatbot is Task 3 of the CodeAlpha Java Programming internship.";
        }

        if (x.contains("grade")) {
            return "The Grade Tracker calculates average, highest, lowest and a letter grade.";
        }

        if (x.contains("stock")) {
            return "The Stock Trading task supports virtual BUY/SELL operations and portfolio tracking.";
        }

        if (x.contains("hotel")) {
            return "The Hotel task supports room categories, booking, cancellation and simulated payment.";
        }

        if (x.contains("thank")) {
            return "You're welcome! Good luck with your internship.";
        }

        if (x.contains("bye")) {
            return "Goodbye! Keep building great Java projects.";
        }

        return "I don't have a trained answer for that yet. "
             + "Try asking about Java, OOP, the internship, stocks, grades or hotels.";
    }
}