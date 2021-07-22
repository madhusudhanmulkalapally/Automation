public class SttringDemo {
    private int age = 90;

    public int getAge() {
        return age;
    }

    public void setAge(int age){
     this.age =  age;
    }

    public int myAge(int ageValue) {
        age = ageValue;
        System.out.print("my age is: "+age);
        return age;
    }

    public void testEx() {
        
        throw new ArithmeticException("test2/test");
    }
//    public SttringDemo(String text) {
//        System.out.print("Welcome to:" +text);
//    }

public static void main(String args[]) {

        SttringDemo s = new SttringDemo();
        s.testEx();


//    SttringDemo st = new SttringDemo();
//    st.setAge(20);
//System.out.print(st.getAge());
//
//
//   System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
//     WebDriver driver = new ChromeDriver();
//     driver.get("https://datatables.net/");
//    WebElement table = driver.findElement(By.cssSelector("[id='example']"));
//    List<WebElement> row = table.findElements(By.tagName("tr"));
//
//    Iterator<WebElement> it = row.iterator();
//    while(it.hasNext()){
//        WebElement roEle = it.next();
//        List<WebElement> cell = roEle.findElements(By.tagName("td"));
//        Iterator<WebElement> cellIt   = cell.iterator();
//        while(cellIt.hasNext()) {
//            cellIt.next().click();
//            String salary  = driver.findElement(By.cssSelector("[class='dtr-data']")).getText();
//            //String value = cellIt.next().getText();
//            System.out.println(salary);
//        }
//    }




}
}
