import java.util.*;
public class Customer
{
    static ArrayList <PizzaPasta> queue= new ArrayList();
    // a growing dynamic collection of objects of PizzaPasta class.
    // Each object will have its specification as per order.
    byte size,crust,flavor,toppings[]; // spec for pizza
    byte sauce,type; // spec for pasta
    public static void main(String args[])
    {
// In main we shall show menu options once; 
    System.out.println(" Greetings! Here is your menu options and pricing. We serve pizzas and pastas.");
    Customer c= new Customer();
// ask repeatedly Customer ready for placing order? true/false; 
//if true call order else stop asking.
    Scanner in=new Scanner(System.in);
    for(;;)
    {
        System.out.println("Is customer ready for placing order?true/false");
        boolean res=in.nextBoolean();
        if(res==false)
        break;
        else
            c.order();
    }
    c.kitchen(); //queue of all orders sent to kitchen
    }
    private void order()
    {
    // Takes orders from customers and stores in the ArrayList queue
    // queue is accessible by kitchen.
        Scanner in=new Scanner(System.in);
        System.out.println("Input 1. for Pizza or 2. Pasta.");
        byte choice=in.nextByte();
        switch (choice)
        {
            case 1:
                System.out.println(" For pizza lovers available:\n 3 sizes (cost 'x' times)-- x=1. Small 2. Medium 3. Large; \n 2 crusts (no charge) -- 1. Thin or 2. Thick; \n 3 flavors -- 1. veg (20x USD) 2. non-veg (25x USD) 3. vegan (15x USD); \n toppings (1 USD each)-- 1.Cheese,2.Mushroom,3.Tomato,4.Jalapeno,5.Spinach \n");
                System.out.println(" Pizza size 1. Small 2. Medium 3. Large?");
                byte size=in.nextByte();
                System.out.println(" Crust 1. Thin or 2. Thick?");
                byte crust=in.nextByte();
                System.out.println("Flavors 1. veg or 2. non-veg or 3. vegan ?");
                byte flavor=in.nextByte();         
                System.out.println("Toppings how many?");
                byte n=in.nextByte();
                byte toppings[]=new byte[n];
                for(byte i=0;i<n;i++)
                {
                    System.out.print("Input choice for topping 1.Cheese,2.Mushroom,3.Tomato,4.Jalapeno,5.Spinach?");
                    toppings[i]=in.nextByte();
                    System.out.println();
                }
                PizzaPasta p= new PizzaPasta(choice,size, crust, flavor,toppings);
                p.pizzaPrice();
                queue.add(p);
            break;
            case 2:
                System.out.println(" For pasta lovers available:\n 2 flavors-- 1. White sauce or 2. Red Sauce (10 USD); \n 2 types (no charge) -- 1. Penne or 2. Ditalini");
                System.out.println(" Pasta sauce 1. White sauce or 2. Red Sauce?");
                byte sauce=in.nextByte();
                System.out.println(" Type 1. Penne or 2. Ditalini?");
                byte type=in.nextByte();                
                p= new PizzaPasta(choice,sauce,type);
                p.pastaPrice();
                queue.add(p);
            break;
            default:
            System.out.println("Sorry! not in menu this time. Please try something else.");
        }
    }
    private void kitchen()
    {
    // kitchen would process the ArrayList queue as first come first serve only.
    // Apologizes if order can't be prep for wrong choices.
    // Else displays order number , order details and price. 
        for (int i=0; i<queue. size();i++)
        {
            // if invalid inputs are given order can't prep
            if(queue.get(i).cost<0 || (queue.get(i).choice!=1 && queue.get(i).choice!=2))
            System.out.println("Apologies, Order:"+(i+1)+" cannot be prep.");
            else
            {
            System.out.println("Enjoy, Order:"+(i+1)+" prepared. Please make payment.");
            if (queue.get(i).choice==1)
            queue.get(i).pizzaDisplay();
            else if (queue.get(i).choice==2)
            queue.get(i).pastaDisplay();
            }
        }
    
    }
}
class PizzaPasta
{
    byte choice; // 1. pizza or 2. pasta
    byte size,crust,flavor,toppings[]; //for Pizza
    byte sauce,type; //for Pasta
    int cost=-1;
    public PizzaPasta(byte choice,byte size,byte crust,byte flavor,byte toppings[]) // constructor for Pizza attributes
    {
        this.choice=choice;this.size=size;this.crust=crust;this.flavor=flavor;this.toppings=toppings;
    }
    public PizzaPasta(byte choice,byte sauce,byte type) // constructor for Pasta attributes
    {
        this.choice=choice;this.sauce=sauce;this.type=type;
    }
    public void pizzaPrice()
    {
// flavor is 1. veg cost =20 2. non-veg=25 3. vegan=15 or any other invalid -1.
        cost=(flavor==1)?20:((flavor==2)?25:(flavor==3)?15:-1);
// for crust size:small=1, medium =2, large=3 same as cost multiplicity for each flavor veg/non-veg/vegan.
        cost=cost*size; 
// add cost for each toppings seperately.
// here any toppings cost 1.00 USD so just multiplying the number of toppings.
        cost=cost+1*toppings.length;
// size not small or medium or large cost invalid
        cost=(size>=1&&size<=3)?cost:-1;
    }
    public void pastaPrice()
    {
        // for sauce is 1. White sauce then cost is 10 USD or 2. Red sauce is 20 USD else invalid i.e. -1 
        cost=(sauce==1)?10:((sauce==2)?20:-1);
        // cost also invalid if pasta type invalid
        cost=(type==1||type==2)?cost:-1;
    }
    public void pizzaDisplay()
    {
        System.out.print("Your Pizza choices: size:"+size+" crust:"+crust+" flavor:"+flavor+" toppings:");
        for (byte t:toppings)
            System.out.print(t+" ");
        System.out.println(" price="+cost+" USD");
    }    
    public void pastaDisplay()
    {
        //this.size=size;this.crust=crust;this.flavor=flavor;this.toppings=toppings;
        System.out.println("Your Pasta choices: sauce="+sauce+" type="+type+" price="+cost+" USD");
    }
}