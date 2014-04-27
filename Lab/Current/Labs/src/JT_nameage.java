
/**
 * JT_nameage - 
 *
 * @author C14Gavin.Delphia
 *
 * @version 1.0 - Jan 23, 2012 at 2:08:31 PM
 */
public class JT_nameage {

  int age;
  String name;

  public JT_nameage(int age, String name) {
    this.age = age;
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object test) {
    if (test instanceof JT_nameage){
      JT_nameage mytest = (JT_nameage) test;
      if (this.getAge() == mytest.getAge() && this.getName().equals(mytest.getName())){
        return true;
      }else{
        return false;
      }
    }else{
      return false;
    }
  }

  @Override
  public String toString() {
    return this.getName() + ", " + this.getAge();
  }
}
