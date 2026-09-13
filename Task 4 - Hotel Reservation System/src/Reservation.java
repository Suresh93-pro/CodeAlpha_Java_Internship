import java.io.Serializable;import java.time.*;
public class Reservation implements Serializable{int id;String guest,room,category,status,payment;LocalDate in,out;double amount;Reservation(int i,String g,String r,String c,LocalDate a,LocalDate b,double p){id=i;guest=g;room=r;category=c;in=a;out=b;amount=p;status="CONFIRMED";payment="PAID (SIMULATION)";}}
