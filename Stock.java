
/**
 * Task 13 question 1 - The class Stock
 * This class represents a stock - arry of foodItem objects.
 *
 * @author Netanel Dahan 313565871
 * @version (2020a)
 */
public class Stock {
    //  declarations 
    private FoodItem[] _stock ;
    private int _noOfItems ;

    private final int MAX_ITEMS_IN_STOCK =100 ;

    // constractors definitions
    /**
     * Default constructor for foodItems arrys of class Stock.
     * The array is initialized to a maximum size, but empty at first.
     */
    public Stock()
    {
        _stock = new FoodItem[MAX_ITEMS_IN_STOCK] ;
        _noOfItems = 0 ;
    }
    // method definitions
    /**
     * Get the number of food items that are actually in the stock 
     * @return the number of fooditems that are actually in the stock
     */
    public int getNumOfItems() {
        return _noOfItems;
    }

    /**
     * Add a new foodItem to the stock.
     * If the stock is full, the foodItem will be not added.
     * @param item the new foodItem to add
     * @return true if able to add the new FoodItem, false otherwise
     */
    public boolean addItem(FoodItem item) {

        for (int i = 0 ; i< _noOfItems ; i++ ){
            if (item.equals (_stock[i])){ 
                // for equals items- Adds a quantity only
                _stock[i].setQuantity (_stock[i].getQuantity() + item.getQuantity());
                return true ;
            }
            //it is new foodItem
            if (_noOfItems == _stock.length) {
                return false;// There is no place for another item
            }
            //else - update new item according the catalogue number
            if (item.getCatalogueNumber() <= _stock[i].getCatalogueNumber()){
                // Moving all the following items one place forward
                for (int j = _noOfItems-1 ; j>= i ; j-- ){
                    _stock[j+1] = _stock[j] ;
                }
                // Placing the new item in its place
                _stock[i] = new FoodItem (item);//avoid aliasing
                _noOfItems++ ;
                return true ;
            }// end of "if" block
        }// end of outter loop
        //The new product has the largest catalog number in the stock
        _stock[_noOfItems] = new FoodItem (item);//avoid aliasing
        _noOfItems++ ;
        return true ;
    }// end of method

    /**
     * Return a string containing a list of product names to order .
     * @param amount The amount of inventory for any small amount of it
     *        has to order the same
     * @return s string containing a list of product names to order
     */
    public String order(int amount) {
        String s = new String ("") ;//default string
        for (int i =0 , n=0 ; i<_noOfItems ;n=0,i++) {
            int quantity = _stock[i].getQuantity() ;
            for (int j =i+1 ; j<_noOfItems  ;j++) {
                if ( _stock[i].getName().equals(_stock[j].getName()) &&
                _stock[i].getCatalogueNumber() == _stock[j].getCatalogueNumber()){
                    quantity += _stock[j].getQuantity() ;
                    n++ ; // Count the number of products whose quantities are counted together
                }
            }// end of inner loop

            if (quantity< amount){ 
                // An additional quantity needs to be ordered from the product
                if (s.equals("")) // at begining of  string "," is no needed 
                    s+= _stock[i].getName() ;
                else 
                    s+= ", " + _stock[i].getName() ;
            }// end of outter "if" block
            i+=n ;
            // Skipping the products tested together with the original product
        }// end of outter loop
        return s ;
    }// end of method

    /**
     * Returns the number of units of products that can be purchased-
     * for a given temperature, according to the quantity.
     *  @param temp the temperature of a certain refrigerator
     *  @return the number of units can be in the frigerator in this temperature 
     */
    public int howMany(int temp) {
        int quantity = 0 ; //default value
        for (int i =0 ; i<_noOfItems ;i++){
            if (temp>= _stock[i].getMinTemperature() &&
            temp <= _stock[i].getMaxTemperature()) 
            // This product can be maintained at a given temperature
                quantity += _stock[i].getQuantity() ;
        }// end of loop
        return quantity ;
    }// end of method

    /**
     * Delete all expired item in the stock.
     * @param d The date by which the method determines which products have expired 
     */
    public void removeAfterDate (Date d){
        for (int i =0 ; i<_noOfItems ;i++) {
            if (_stock[i].getExpiryDate().before(d)){// expired item
                removeItem (_stock[i]) ;
                // Deleting a product updates a new item to the same location,
                // so the same place should be checked again
                i-- ;
            }// end of "if" block
        }// end of loop
    } // end of method

    /**
     * Search the food item at this stock that most expensive,
     * if the stock is empty, the method return null item.
     * @return expensiveItem the most expensive foodItem in the stock
     */
    public FoodItem mostExpensive() {
        if (_noOfItems== 0) 
            return null ; // there no item in the stock

        FoodItem expensiveItem = new FoodItem (_stock[0]) ; //initial value
        for (int i = 1; i < _noOfItems; i++) {
            if (expensiveItem.isCheaper(_stock[i]) ){
                //found a more expensive product than any of its predecessors
                expensiveItem = new FoodItem (_stock[i]) ; ;
            } 
        }// end of loop
        return expensiveItem ;
    }// end of method

    /**
     * Counts the number of products in stock.
     * @return quantity the number of products in stock
     */
    public int howManyPieces() {
        int quantity =0 ; //default value
        for (int i = 0; i < _noOfItems; i++){
            quantity += _stock[i].getQuantity() ;
        }
        return quantity ;
    }// end of method

    /**
     * Returns a String that represents this stock.
     * @return String s that represents all of food items at this stock in the following format:
     * FoodItem: milk CatalogueNumber: 1234 ProductionDate: 14/12/2019 ExpiryDate: 21/12/2019 Quantity: 3
     */
    public String toString(){
        String s="";
        for (int i = 0; i < _noOfItems; i++) {
            s+=_stock[i].toString()+"\n";
        }
        return s;
    }// end of method

    /**
     * Updates the stock after sale.
     * @param itemsList arry of String representing the products sold
     */
    public void updateStock(String[] itemsList){
        // Search within the stock of each product in the list
        for (int i = 0; i < itemsList.length; i++) { 
            boolean update = false ; // stop the inner loop after the update
            for (int j = 0 ; j < _noOfItems && update == false ; j++){
                if (itemsList[i].equals(_stock[j].getName())) {
                    //Misses one item from the fooditem according the list
                    _stock[j].setQuantity(_stock[j].getQuantity()-1) ;
                    update = true ;
                    //Check if the item is out of stock after an update
                    if (_stock[j].getQuantity() == 0 ) 
                        removeItem(_stock[j]) ;
                }// end of first "if" block
            }// end of inner loop
        }// end of outter loop
    }// end of method

    /**
    Returns the minimum temperature at which the refrigerator should contain all fooditems.
    If it is not possible the Integer.MAX_VALUE will be returned
     * @return minTemperature the minimum temperature at which the refrigerator should contain all fooditems
     */
    public int getTempOfStock() {
        if (_noOfItems == 0) // there is no item in the stock
            return Integer.MAX_VALUE ;
        //initial values
        int maxTemperature=_stock[0].getMaxTemperature() ;
        int minTemperature=_stock[0].getMinTemperature() ;
        for (int i = 0; i < _noOfItems; i++) {
            if(maxTemperature > _stock[i].getMaxTemperature() )
            // found new max Temperature common to all items
                maxTemperature = _stock[i].getMaxTemperature() ;

            if ( minTemperature < _stock[i].getMinTemperature())
            // found new max Temperature common to all items
                minTemperature = _stock[i].getMinTemperature() ;
        }// end of loop

        if (minTemperature <= maxTemperature )
            return minTemperature ;
        //else- min>max --  No found any temprature common to all items 
        return Integer.MAX_VALUE ;

    }// end of method

    // Deletes a specific product from the array,
    // and makes sure there are no holes and "trash" values in the array.
    private  boolean removeItem (FoodItem item){ 
        for (int i = 0 ; i< _noOfItems ; i++ ){
            if (item.equals(_stock[i])){
                //Moving all the following items one place backward
                for (int j = i ; j <  _noOfItems - 1 ; j++) {  
                    _stock[j] = _stock[j+1] ;
                }
            }// end of method
        }// end of outter loop
        // Delete "trash" value from arry
        _stock[_noOfItems] = null ;
        _noOfItems -- ;
        return true ;
    }// end of method
}// end of class Stock

