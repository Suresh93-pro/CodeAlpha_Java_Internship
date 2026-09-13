import java.time.*;
public class Trade{String type,symbol;int qty;double price,total;LocalDateTime time;Trade(String t,String s,int q,double p){type=t;symbol=s;qty=q;price=p;total=q*p;time=LocalDateTime.now();}}
