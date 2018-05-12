package pl.marczyk.patterns.observer.subjects;

/**
 * Contains data about single discount.
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0 
 */
public class DiscountUnit {
    
    private String discountSourceName;
    private String productName;
    private float  discountPercentage;
    private float  priceBefore;
    private float  priceNow;
    
    public DiscountUnit(String sourceName){
        this.discountSourceName = sourceName;
    }
    
    // shouldnt be used public void setDiscountSource(String discountSource)        { this.discountSourceName  = discountSource;   }
    public void setProductName(String discountProduct)          { this.productName  = discountProduct;         }
    public void setDiscountPercentage(float discountPercentage) { this.discountPercentage = discountPercentage;}
    public void setPriceBefore(float priceBefore)               { this.priceBefore        = priceBefore;       }
    public void setPriceNow(float priceNow)                     { this.priceNow           = priceNow;          }

    public String getDiscountSource()     { return this.discountSourceName; }
    public String getProductName()        { return this.productName;        }
    public float  getDiscountPercentage() { return this.discountPercentage; }
    public float  getPriceBefore()        { return this.priceBefore;        }
    public float  getPriceNow()           { return this.priceNow;           }
    
}
