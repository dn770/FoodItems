/**
 * Task 12 question 2 - The class FoodItem
 * This class represents a foodItem object. 
 *
 * @Author Netanel Dahan 313565871
 * @Version (2020a)
 */
public class FoodItem {
    // declarations
    private String _name ;
    private long _catalogueNumber ;
    private int _quantity ;
    private Date _productionDate ;
    private Date _expiryDate ;
    private int _minTemperature ;  
    private int _maxTemperature ;
    private int _price ;

    // default values
    private final int DEFAULT_PRICE = 1 ;
    private final String DEFAULT_NAME = "item" ;
    private final long DEFAULT_CATALOGUE_NUMBER = 9999 ;
    private final int DEFAULT_QUANTITY = 0 ;

    // min and max values
    private final int MIN_NAME_LENGTH = 1 ;
    private final int MIN_PRICE = 1 ;
    private final int MIN_QUANTITY = 0 ;
    private final long MIN_CATALOGUE_NUMBER = 1000 ;
    private final long MAX_CATALOGUE_NUMBER = 9999 ;

    // constractors definitions
    /**
     * Constructor that creates a new FoodItem object.
     * @param name  name of food item
     * @param catalogueNumber  catalogue number of food item
     * @param quantity  quantity of food item
     * @param productionDate  production date
     * @param expiryDate  expiry date
     * @param minTemperature  minimum storage temperature
     * @param maxTemperature  maximum storage temperature
     * @param price  unit price
     */
    public FoodItem (String name, long catalogueNumber,  int quantity, Date productionDate,
    Date expiryDate, int minTemperature, int maxTemperature, int price )
    {
        // name
        if ( name.length() >= MIN_NAME_LENGTH)
            _name = name ;
        else // not valid length of name
            _name = DEFAULT_NAME ;
        //catalogueNumber
        if ( catalogueNumber>= MIN_CATALOGUE_NUMBER &&
        catalogueNumber<= MAX_CATALOGUE_NUMBER )
            _catalogueNumber=catalogueNumber ;
        else // not valid catalogueNumber
            _catalogueNumber = DEFAULT_CATALOGUE_NUMBER ;
        // quantity
        if (quantity >= MIN_QUANTITY) 
            _quantity =quantity;
        else //quantity < MIN_QUANTITY
            _quantity = DEFAULT_QUANTITY ;
        // production date && expiry date
        _productionDate = new Date (productionDate) ;

        if (expiryDate.before(productionDate)) // not valid expiryDate
            _expiryDate = new Date (productionDate.tomorrow()) ;     
        else // valid expiryDate - not before the productionDate
            _expiryDate= new Date (expiryDate) ;
        // min temperature && max temperature
        if (minTemperature <= maxTemperature ){
            _minTemperature= minTemperature ;  
            _maxTemperature= maxTemperature ;
        }
        else { //min > max - not valid values
            _minTemperature= maxTemperature;  
            _maxTemperature=minTemperature ;
        }
        // price
        if (price >= MIN_PRICE)
            _price= price ;
        else // price < MIN_PRICE
            _price= DEFAULT_PRICE ;
    } // end of the constractor

    /**
     * Copy constructor for Fooditem.
     * Construct a food item with the same variables as another fooditem.
     * @param other The foodItem object from which to construct the new food item
     */
    public FoodItem (FoodItem other) {
        _name = other._name;
        _catalogueNumber = other._catalogueNumber;
        _quantity = other._quantity ;
        _productionDate = new Date (other._productionDate);
        _expiryDate = new Date (other._expiryDate) ;
        _minTemperature = other._minTemperature;  
        _maxTemperature = other._maxTemperature ;
        _price = other._price ;
    }

    // method definitions
    /**
     * Returns the catalogue number of the food item.
     * @return The catalogue number of the food item
     */
    public  long getCatalogueNumber() {
        return _catalogueNumber ;
    }

    /**
     * Returns the name of the food item.
     * @return The name of the food item
     */
    public String getName(){
        return _name ;
    }

    /**
     * Returns the quantity of the food item.
     * @return The quantity of the food item
     */
    public int getQuantity() {
        return _quantity ;
    }

    /**
     * Returns the production date of the food item.
     * @return The production date of the food item
     */
    public Date getProductionDate(){
        return new Date(_productionDate) ;
    }

    /**
     * Returns the expiry date of the food item.
     * @return The expiry date of the food item
     */
    public Date getExpiryDate (){
        return new Date(_expiryDate) ;
    }

    /**
     * Returns the minimal temperature of the food item.
     * @return The minimal temperature of the food item
     */
    public  int getMinTemperature () {
        return _minTemperature ;
    }

    /**
     * Returns the maximal temperature of the food item.
     * @return The maximal temperature of the food item
     */
    public  int getMaxTemperature (){
        return _maxTemperature ;
    }

    /**
     * Returns the price of the food item.
     * @return The price of the food item
     */
    public int getPrice () {
        return _price ;
    }

    /**
     * Changes the quantity of the food item. (only if not negative)
     * @param newQuantity - the quantity value to be set
     */
    public void setQuantity (int newQuantity){
        if (newQuantity >= MIN_QUANTITY) 
            _quantity =newQuantity ;
    }

    /**
     * Changes the production date of the food item.
     *  (only if not after expiry date.) 
     * @param d - production date value to be set
     */
    public void setProductionDate (Date d) {
        if (!d.after(_expiryDate))
            _productionDate= new Date (d) ;

    }

    /**
     * Changes the expiry date of the food item.
     *  (only if not before production date.)
     * @param d - expiry date value to be set
     */
    public void setExpiryDate (Date d) {
        if (!d.before(_productionDate))
            _expiryDate= new Date (d) ;
    }

    /**
     * Changes the price of the food item. (only if positive.)   
     * @param newPrice - price value to be set
     */
    public void setPrice (int newPrice){
        if (newPrice >= MIN_PRICE)
            _price= newPrice ;
    }

    /**
     * Check if the received food item is equal to this date, 
     *  excluding the quantity values. 
     * @param other - the food item to be compared with this food item to
     * @return true if the received food item is  equal to this food item
     */
    public boolean equals (FoodItem other){ 
        if (_name.equals(other._name) && 
        _catalogueNumber == other._catalogueNumber &&
        _productionDate.equals(other._productionDate) &&
        _expiryDate.equals(other._expiryDate) &&
        _minTemperature == other._minTemperature && 
        _maxTemperature == other._maxTemperature && 
        _price == other._price )
            return true ;
        // else - there are not equals
        return false ;
    }

    /**
     * check if this food item is fresh on the date d.
     * @param d - date to check
     * @return true if this food item is fresh on the date d
     */
    public boolean isFresh (Date d){
        if (d.before(_productionDate)||d.after(_expiryDate)) 
        // not created or expired
            return false ;
        // else - is fresh
        return true ;
    }

    /**
     * Returns a String that represents this food item.
     * @return String that represents this food item in the following format:
     * FoodItem: milk CatalogueNumber: 1234 ProductionDate: 14/12/2019 ExpiryDate: 21/12/2019 Quantity: 3
     */
    public String toString() {
        String item="";
        item+= "FoodItem: " + _name + "\t" ;
        item+= "CatalogueNumber: " + _catalogueNumber + "\t" ;
        item+= "ProductionDate: " + _productionDate.toString() + "\t" ;
        item+= "ExpiryDate: " +_expiryDate.toString() + "\t" ;
        item+= "Quantity: " + _quantity ; 
        return item ;
    }

    /**
     * Check if this food item is older than other food item. 
     * (compare production dates).
     * @param other - food item to compare this food item to 
     * @return true if this food item is older than other date
     */
    public boolean olderFoodItem (FoodItem other) {
        if (_productionDate.before(other._productionDate))
            return true ;
        else // this production date after or equals the other
            return false ;
    }

    /**
     * Returns the number of units of products that can be purchased-
     * for a given amount, according to the quantity.
     *  @param amount - amount to purchase
     *  @return the number of units can be purchased 
     */
    public int howManyItems (int amount) {
        if ( amount / _price <=_quantity)
            return amount / _price ;
        else // not enough quantity for this amount
            return _quantity ;
    }

    /**
     * Check if this food item is cheaper than other food item.
     * @param other - food item to compare this food item to
     * @return true if this food item is cheaper than other date
     */
    public boolean isCheaper (FoodItem other){
        if (_price <= other._price)
            return true ;
        // else - this price > other price
        return false ;
    }
}// end of class FoodItem