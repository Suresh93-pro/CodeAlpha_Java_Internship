import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
public class UI {
    static final Color BG=new Color(8,14,28), CARD=new Color(16,26,46), ACCENT=new Color(112,92,255), TEXT=new Color(238,244,255), MUTED=new Color(157,174,202);
    static void style(JFrame f){f.getContentPane().setBackground(BG);}
    static JPanel card(){JPanel p=new JPanel();p.setBackground(CARD);p.setBorder(new CompoundBorder(new LineBorder(new Color(38,57,86),1,true),new EmptyBorder(16,16,16,16)));return p;}
    static JButton button(String s){JButton b=new JButton(s);b.setForeground(Color.WHITE);b.setBackground(ACCENT);b.setFocusPainted(false);b.setBorder(new EmptyBorder(10,16,10,16));return b;}
    static JLabel title(String s){JLabel l=new JLabel(s);l.setForeground(TEXT);l.setFont(new Font("Segoe UI",Font.BOLD,24));return l;}
    static JLabel muted(String s){JLabel l=new JLabel(s);l.setForeground(MUTED);return l;}
    static void msg(Component p,String s){JOptionPane.showMessageDialog(p,s,"CodeAlpha",JOptionPane.INFORMATION_MESSAGE);}
    static JTextField input(String hint){JTextField x=new JTextField();x.setToolTipText(hint);x.setBackground(new Color(7,15,29));x.setForeground(TEXT);x.setCaretColor(Color.WHITE);x.setBorder(new CompoundBorder(new LineBorder(new Color(45,66,96)),new EmptyBorder(8,10,8,10)));return x;}
}
