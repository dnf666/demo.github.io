import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * ˫������ʹ��
 */
public class ListedListUse {
    public static void main(String[] args) {
    LinkedList<Student> link = new LinkedList<Student>();
  for(int k=1;k<=34567;k++){
        link.add(new Student("i am",k));
    }
    Iterator<Student> it = link.iterator();
    long time1 = System.currentTimeMillis();
  while(it.hasNext()){
        Student s = it.next();
    }
    long time2 = System.currentTimeMillis();
    sop("ʹ�õ�����������ʱ��"+(time2-time1)+"����");

    time1 = System.currentTimeMillis();
  for(int i=0;i<link.size();i++){
        Student s2 = link.get(i);
    }
    time2 = System.currentTimeMillis();
    sop("ʹ��get������ʱ��"+(time2-time1)+"����");

}

    public static void sop(Object obj){
        System.out.println(obj);
    }
}
class Student{
     String name;
     int number;
    Student(String name,int number){
        this.name = name;
        this.number = number;

    }

}
