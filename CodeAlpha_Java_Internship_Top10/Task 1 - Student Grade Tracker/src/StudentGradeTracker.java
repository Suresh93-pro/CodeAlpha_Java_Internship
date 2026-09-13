import javax.swing.*;import java.awt.*;import java.io.*;import java.util.*;
public class StudentGradeTracker extends JFrame{
 ArrayList<Student> students=new ArrayList<>(); DefaultListModel<String> model=new DefaultListModel<>(); JList<String> list=new JList<>(model);
 JTextField name=UI.input("Student name"), grade=UI.input("Grade 0-100");
 public StudentGradeTracker(){setTitle("CodeAlpha • Student Grade Tracker");setSize(850,560);setLocationRelativeTo(null);UI.style(this);
  JPanel top=UI.card();top.setLayout(new BorderLayout(10,10));top.add(UI.title("STUDENT GRADE TRACKER"),BorderLayout.WEST);top.add(UI.muted("Add • Analyze • Report"),BorderLayout.EAST);
  JPanel form=UI.card();form.setLayout(new GridLayout(2,3,10,10));JButton add=UI.button("Add Grade"),newStudent=UI.button("New Student"),report=UI.button("Summary Report");
  form.add(name);form.add(grade);form.add(add);form.add(newStudent);form.add(report);
  list.setBackground(new Color(10,19,34));list.setForeground(UI.TEXT);list.setFont(new Font("Segoe UI",Font.PLAIN,15));
  add.addActionListener(e->{try{String n=name.getText().trim();if(n.isEmpty())throw new Exception("Enter a student name.");Student s=find(n);if(s==null){s=new Student(n);students.add(s);}s.addGrade(Double.parseDouble(grade.getText()));refresh();grade.setText("");save();}catch(Exception x){JOptionPane.showMessageDialog(this,x.getMessage(),"Input Error",JOptionPane.ERROR_MESSAGE);}});
  newStudent.addActionListener(e->{String n=JOptionPane.showInputDialog(this,"Student name:");if(n!=null&&!n.isBlank()){students.add(new Student(n.trim()));refresh();save();}});
  report.addActionListener(e->{if(students.isEmpty()){UI.msg(this,"No students yet.");return;}StringBuilder b=new StringBuilder("STUDENT SUMMARY\\n\\n");for(Student s:students)b.append(s.getName()).append(" | Avg: ").append(String.format("%.2f",s.avg())).append(" | High: ").append(s.high()).append(" | Low: ").append(s.low()).append(" | Grade: ").append(s.letter()).append("\\n");JTextArea a=new JTextArea(b.toString());a.setEditable(false);a.setFont(new Font("Monospaced",0,14));JOptionPane.showMessageDialog(this,new JScrollPane(a),"Summary",JOptionPane.INFORMATION_MESSAGE);});
  setLayout(new BorderLayout(16,16));add(top,BorderLayout.NORTH);add(form,BorderLayout.SOUTH);add(new JScrollPane(list),BorderLayout.CENTER);load();refresh();
 }
 Student find(String n){for(Student s:students)if(s.getName().equalsIgnoreCase(n))return s;return null;}
 void refresh(){model.clear();for(Student s:students)model.addElement(String.format("%-22s Avg %-6.2f High %-5.1f Low %-5.1f  Grade %s",s.getName(),s.avg(),s.high(),s.low(),s.letter()));}
 void save(){try(ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream("students.dat"))){o.writeObject(students);}catch(Exception ignored){}}
 void load(){try(ObjectInputStream o=new ObjectInputStream(new FileInputStream("students.dat"))){students=(ArrayList<Student>)o.readObject();}catch(Exception ignored){}}
 public static void main(String[]a){SwingUtilities.invokeLater(()->new StudentGradeTracker().setVisible(true));}
}
