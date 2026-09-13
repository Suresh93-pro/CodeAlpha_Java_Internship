import java.io.Serializable;
import java.util.*;
public class Student implements Serializable{
    private String name; private ArrayList<Double> grades=new ArrayList<>();
    public Student(String n){name=n;}
    public String getName(){return name;}
    public ArrayList<Double> getGrades(){return grades;}
    public void addGrade(double g){if(g<0||g>100)throw new IllegalArgumentException("Grade must be 0–100.");grades.add(g);}
    public double avg(){return grades.stream().mapToDouble(x->x).average().orElse(0);}
    public double high(){return grades.stream().mapToDouble(x->x).max().orElse(0);}
    public double low(){return grades.stream().mapToDouble(x->x).min().orElse(0);}
    public String letter(){double a=avg();return a>=90?"A+":a>=80?"A":a>=70?"B":a>=60?"C":a>=50?"D":"F";}
}
