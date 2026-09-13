import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class TradingPlatform extends JFrame {

    ArrayList<Stock> stocks = new ArrayList<>();
    ArrayList<Trade> history = new ArrayList<>();
    double cash = 100000;

    DefaultListModel<String> m = new DefaultListModel<>();
    JList<String> list = new JList<>(m);

    JTextField symbol = UI.input("Symbol");
    JTextField qty = UI.input("Quantity");

    public TradingPlatform() {

        setTitle("CodeAlpha • Stock Trading Platform");
        setSize(950, 600);
        setLocationRelativeTo(null);
        UI.style(this);

        // Demo market data
        stocks.add(new Stock("AAPL", "Apple", 225, 1.2));
        stocks.add(new Stock("MSFT", "Microsoft", 510, 0.8));
        stocks.add(new Stock("NVDA", "NVIDIA", 175, 2.4));
        stocks.add(new Stock("AMZN", "Amazon", 230, -0.4));
        stocks.add(new Stock("GOOGL", "Alphabet", 240, 1.1));

        // Header
        JPanel head = UI.card();
        head.setLayout(new BorderLayout());

        head.add(
            UI.title("STOCK TRADING PLATFORM"),
            BorderLayout.WEST
        );

        head.add(
            UI.muted("Virtual cash: ₹100,000"),
            BorderLayout.EAST
        );

        // Action buttons
        JPanel actions = UI.card();

        JButton buy = UI.button("BUY");
        JButton sell = UI.button("SELL");
        JButton portfolio = UI.button("Portfolio");
        JButton historyBtn = UI.button("Trade History");

        actions.add(symbol);
        actions.add(qty);
        actions.add(buy);
        actions.add(sell);
        actions.add(portfolio);
        actions.add(historyBtn);

        // Button actions
        buy.addActionListener(e -> trade("BUY"));
        sell.addActionListener(e -> trade("SELL"));
        portfolio.addActionListener(e -> portfolio());
        historyBtn.addActionListener(e -> history());

        // Window layout
        setLayout(new BorderLayout(14, 14));

        add(head, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);

        refresh();
    }

    // Find stock by symbol
    Stock find(String s) {

        for (Stock x : stocks) {
            if (x.symbol.equalsIgnoreCase(s)) {
                return x;
            }
        }

        return null;
    }

    // Refresh stock dashboard
    void refresh() {

        m.clear();

        for (Stock s : stocks) {

            m.addElement(
                String.format(
                    "%-7s %-12s ₹%-8.2f  %+.2f%%  Holdings: %d",
                    s.symbol,
                    s.name,
                    s.price,
                    s.change,
                    s.owned
                )
            );
        }
    }

    // BUY / SELL operation
    void trade(String type) {

        try {

            Stock s = find(symbol.getText().trim());
            int q = Integer.parseInt(qty.getText().trim());

            if (s == null || q <= 0) {
                throw new Exception("Invalid stock or quantity.");
            }

            // BUY validation
            if (type.equals("BUY") && cash < q * s.price) {
                throw new Exception("Insufficient virtual cash.");
            }

            // SELL validation
            if (type.equals("SELL") && s.owned < q) {
                throw new Exception("Not enough holdings.");
            }

            // Execute transaction
            if (type.equals("BUY")) {

                cash -= q * s.price;
                s.owned += q;

            } else {

                cash += q * s.price;
                s.owned -= q;
            }

            // Record transaction
            history.add(
                new Trade(
                    type,
                    s.symbol,
                    q,
                    s.price
                )
            );

            refresh();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Trade Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Display portfolio
    void portfolio() {

        StringBuilder b = new StringBuilder();

        b.append("PORTFOLIO\n\n");

        b.append(
            "Cash: ₹"
        ).append(
            String.format("%.2f", cash)
        ).append("\n");

        double v = 0;

        for (Stock s : stocks) {

            if (s.owned > 0) {

                double x = s.owned * s.price;

                v += x;

                b.append(s.symbol)
                 .append(" × ")
                 .append(s.owned)
                 .append(" = ₹")
                 .append(String.format("%.2f", x))
                 .append("\n");
            }
        }

        b.append("\n");

        b.append("Portfolio value: ₹")
         .append(String.format("%.2f", v));

        UI.msg(this, b.toString());
    }

    // Display trade history
    void history() {

        StringBuilder b = new StringBuilder();

        for (Trade t : history) {

            b.append(t.time)
             .append(" | ")
             .append(t.type)
             .append(" | ")
             .append(t.symbol)
             .append(" | ")
             .append(t.qty)
             .append(" @ ₹")
             .append(t.price)
             .append("\n");
        }

        UI.msg(
            this,
            b.length() == 0
                ? "No trades yet."
                : b.toString()
        );
    }

    public static void main(String[] a) {

        SwingUtilities.invokeLater(
            () -> new TradingPlatform().setVisible(true)
        );
    }
}