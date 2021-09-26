package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the sweetHouse type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "sweetHouses")
public final class sweetHouse implements Model {
  public static final QueryField ID = field("sweetHouse", "id");
  public static final QueryField AREA = field("sweetHouse", "area");
  public static final QueryField LOCATION = field("sweetHouse", "location");
  public static final QueryField NUMBER_OF_ROOMS = field("sweetHouse", "numberOfRooms");
  public static final QueryField FLOORS = field("sweetHouse", "floors");
  public static final QueryField PRICE = field("sweetHouse", "price");
  public static final QueryField AGE_OF_BUILD = field("sweetHouse", "ageOfBuild");
  public static final QueryField POOL = field("sweetHouse", "pool");
  public static final QueryField RENT_OF_SELL = field("sweetHouse", "rentOfSell");
  public static final QueryField IMAGE = field("sweetHouse", "image");
  public static final QueryField BALCONY = field("sweetHouse", "balcony");
  public static final QueryField MORE_INFO = field("sweetHouse", "moreInfo");
  public static final QueryField TYPE = field("sweetHouse", "type");
  public static final QueryField EMAIL = field("sweetHouse", "email");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String area;
  private final @ModelField(targetType="String", isRequired = true) String location;
  private final @ModelField(targetType="String", isRequired = true) String numberOfRooms;
  private final @ModelField(targetType="String", isRequired = true) String floors;
  private final @ModelField(targetType="Int", isRequired = true) Integer price;
  private final @ModelField(targetType="String", isRequired = true) String ageOfBuild;
  private final @ModelField(targetType="Boolean", isRequired = true) Boolean pool;
  private final @ModelField(targetType="String", isRequired = true) String rentOfSell;
  private final @ModelField(targetType="String", isRequired = true) List<String> image;
  private final @ModelField(targetType="Boolean", isRequired = true) Boolean balcony;
  private final @ModelField(targetType="String") String moreInfo;
  private final @ModelField(targetType="String", isRequired = true) String type;
  private final @ModelField(targetType="String", isRequired = true) String email;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getArea() {
      return area;
  }
  
  public String getLocation() {
      return location;
  }
  
  public String getNumberOfRooms() {
      return numberOfRooms;
  }
  
  public String getFloors() {
      return floors;
  }
  
  public Integer getPrice() {
      return price;
  }
  
  public String getAgeOfBuild() {
      return ageOfBuild;
  }
  
  public Boolean getPool() {
      return pool;
  }
  
  public String getRentOfSell() {
      return rentOfSell;
  }
  
  public List<String> getImage() {
      return image;
  }
  
  public Boolean getBalcony() {
      return balcony;
  }
  
  public String getMoreInfo() {
      return moreInfo;
  }
  
  public String getType() {
      return type;
  }
  
  public String getEmail() {
      return email;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private sweetHouse(String id, String area, String location, String numberOfRooms, String floors, Integer price, String ageOfBuild, Boolean pool, String rentOfSell, List<String> image, Boolean balcony, String moreInfo, String type, String email) {
    this.id = id;
    this.area = area;
    this.location = location;
    this.numberOfRooms = numberOfRooms;
    this.floors = floors;
    this.price = price;
    this.ageOfBuild = ageOfBuild;
    this.pool = pool;
    this.rentOfSell = rentOfSell;
    this.image = image;
    this.balcony = balcony;
    this.moreInfo = moreInfo;
    this.type = type;
    this.email = email;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      sweetHouse sweetHouse = (sweetHouse) obj;
      return ObjectsCompat.equals(getId(), sweetHouse.getId()) &&
              ObjectsCompat.equals(getArea(), sweetHouse.getArea()) &&
              ObjectsCompat.equals(getLocation(), sweetHouse.getLocation()) &&
              ObjectsCompat.equals(getNumberOfRooms(), sweetHouse.getNumberOfRooms()) &&
              ObjectsCompat.equals(getFloors(), sweetHouse.getFloors()) &&
              ObjectsCompat.equals(getPrice(), sweetHouse.getPrice()) &&
              ObjectsCompat.equals(getAgeOfBuild(), sweetHouse.getAgeOfBuild()) &&
              ObjectsCompat.equals(getPool(), sweetHouse.getPool()) &&
              ObjectsCompat.equals(getRentOfSell(), sweetHouse.getRentOfSell()) &&
              ObjectsCompat.equals(getImage(), sweetHouse.getImage()) &&
              ObjectsCompat.equals(getBalcony(), sweetHouse.getBalcony()) &&
              ObjectsCompat.equals(getMoreInfo(), sweetHouse.getMoreInfo()) &&
              ObjectsCompat.equals(getType(), sweetHouse.getType()) &&
              ObjectsCompat.equals(getEmail(), sweetHouse.getEmail()) &&
              ObjectsCompat.equals(getCreatedAt(), sweetHouse.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), sweetHouse.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getArea())
      .append(getLocation())
      .append(getNumberOfRooms())
      .append(getFloors())
      .append(getPrice())
      .append(getAgeOfBuild())
      .append(getPool())
      .append(getRentOfSell())
      .append(getImage())
      .append(getBalcony())
      .append(getMoreInfo())
      .append(getType())
      .append(getEmail())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("sweetHouse {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("area=" + String.valueOf(getArea()) + ", ")
      .append("location=" + String.valueOf(getLocation()) + ", ")
      .append("numberOfRooms=" + String.valueOf(getNumberOfRooms()) + ", ")
      .append("floors=" + String.valueOf(getFloors()) + ", ")
      .append("price=" + String.valueOf(getPrice()) + ", ")
      .append("ageOfBuild=" + String.valueOf(getAgeOfBuild()) + ", ")
      .append("pool=" + String.valueOf(getPool()) + ", ")
      .append("rentOfSell=" + String.valueOf(getRentOfSell()) + ", ")
      .append("image=" + String.valueOf(getImage()) + ", ")
      .append("balcony=" + String.valueOf(getBalcony()) + ", ")
      .append("moreInfo=" + String.valueOf(getMoreInfo()) + ", ")
      .append("type=" + String.valueOf(getType()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static AreaStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static sweetHouse justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new sweetHouse(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      area,
      location,
      numberOfRooms,
      floors,
      price,
      ageOfBuild,
      pool,
      rentOfSell,
      image,
      balcony,
      moreInfo,
      type,
      email);
  }
  public interface AreaStep {
    LocationStep area(String area);
  }
  

  public interface LocationStep {
    NumberOfRoomsStep location(String location);
  }
  

  public interface NumberOfRoomsStep {
    FloorsStep numberOfRooms(String numberOfRooms);
  }
  

  public interface FloorsStep {
    PriceStep floors(String floors);
  }
  

  public interface PriceStep {
    AgeOfBuildStep price(Integer price);
  }
  

  public interface AgeOfBuildStep {
    PoolStep ageOfBuild(String ageOfBuild);
  }
  

  public interface PoolStep {
    RentOfSellStep pool(Boolean pool);
  }
  

  public interface RentOfSellStep {
    ImageStep rentOfSell(String rentOfSell);
  }
  

  public interface ImageStep {
    BalconyStep image(List<String> image);
  }
  

  public interface BalconyStep {
    TypeStep balcony(Boolean balcony);
  }
  

  public interface TypeStep {
    EmailStep type(String type);
  }
  

  public interface EmailStep {
    BuildStep email(String email);
  }
  

  public interface BuildStep {
    sweetHouse build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep moreInfo(String moreInfo);
  }
  

  public static class Builder implements AreaStep, LocationStep, NumberOfRoomsStep, FloorsStep, PriceStep, AgeOfBuildStep, PoolStep, RentOfSellStep, ImageStep, BalconyStep, TypeStep, EmailStep, BuildStep {
    private String id;
    private String area;
    private String location;
    private String numberOfRooms;
    private String floors;
    private Integer price;
    private String ageOfBuild;
    private Boolean pool;
    private String rentOfSell;
    private List<String> image;
    private Boolean balcony;
    private String type;
    private String email;
    private String moreInfo;
    @Override
     public sweetHouse build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new sweetHouse(
          id,
          area,
          location,
          numberOfRooms,
          floors,
          price,
          ageOfBuild,
          pool,
          rentOfSell,
          image,
          balcony,
          moreInfo,
          type,
          email);
    }
    
    @Override
     public LocationStep area(String area) {
        Objects.requireNonNull(area);
        this.area = area;
        return this;
    }
    
    @Override
     public NumberOfRoomsStep location(String location) {
        Objects.requireNonNull(location);
        this.location = location;
        return this;
    }
    
    @Override
     public FloorsStep numberOfRooms(String numberOfRooms) {
        Objects.requireNonNull(numberOfRooms);
        this.numberOfRooms = numberOfRooms;
        return this;
    }
    
    @Override
     public PriceStep floors(String floors) {
        Objects.requireNonNull(floors);
        this.floors = floors;
        return this;
    }
    
    @Override
     public AgeOfBuildStep price(Integer price) {
        Objects.requireNonNull(price);
        this.price = price;
        return this;
    }
    
    @Override
     public PoolStep ageOfBuild(String ageOfBuild) {
        Objects.requireNonNull(ageOfBuild);
        this.ageOfBuild = ageOfBuild;
        return this;
    }
    
    @Override
     public RentOfSellStep pool(Boolean pool) {
        Objects.requireNonNull(pool);
        this.pool = pool;
        return this;
    }
    
    @Override
     public ImageStep rentOfSell(String rentOfSell) {
        Objects.requireNonNull(rentOfSell);
        this.rentOfSell = rentOfSell;
        return this;
    }
    
    @Override
     public BalconyStep image(List<String> image) {
        Objects.requireNonNull(image);
        this.image = image;
        return this;
    }
    
    @Override
     public TypeStep balcony(Boolean balcony) {
        Objects.requireNonNull(balcony);
        this.balcony = balcony;
        return this;
    }
    
    @Override
     public EmailStep type(String type) {
        Objects.requireNonNull(type);
        this.type = type;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep moreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String area, String location, String numberOfRooms, String floors, Integer price, String ageOfBuild, Boolean pool, String rentOfSell, List<String> image, Boolean balcony, String moreInfo, String type, String email) {
      super.id(id);
      super.area(area)
        .location(location)
        .numberOfRooms(numberOfRooms)
        .floors(floors)
        .price(price)
        .ageOfBuild(ageOfBuild)
        .pool(pool)
        .rentOfSell(rentOfSell)
        .image(image)
        .balcony(balcony)
        .type(type)
        .email(email)
        .moreInfo(moreInfo);
    }
    
    @Override
     public CopyOfBuilder area(String area) {
      return (CopyOfBuilder) super.area(area);
    }
    
    @Override
     public CopyOfBuilder location(String location) {
      return (CopyOfBuilder) super.location(location);
    }
    
    @Override
     public CopyOfBuilder numberOfRooms(String numberOfRooms) {
      return (CopyOfBuilder) super.numberOfRooms(numberOfRooms);
    }
    
    @Override
     public CopyOfBuilder floors(String floors) {
      return (CopyOfBuilder) super.floors(floors);
    }
    
    @Override
     public CopyOfBuilder price(Integer price) {
      return (CopyOfBuilder) super.price(price);
    }
    
    @Override
     public CopyOfBuilder ageOfBuild(String ageOfBuild) {
      return (CopyOfBuilder) super.ageOfBuild(ageOfBuild);
    }
    
    @Override
     public CopyOfBuilder pool(Boolean pool) {
      return (CopyOfBuilder) super.pool(pool);
    }
    
    @Override
     public CopyOfBuilder rentOfSell(String rentOfSell) {
      return (CopyOfBuilder) super.rentOfSell(rentOfSell);
    }
    
    @Override
     public CopyOfBuilder image(List<String> image) {
      return (CopyOfBuilder) super.image(image);
    }
    
    @Override
     public CopyOfBuilder balcony(Boolean balcony) {
      return (CopyOfBuilder) super.balcony(balcony);
    }
    
    @Override
     public CopyOfBuilder type(String type) {
      return (CopyOfBuilder) super.type(type);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder moreInfo(String moreInfo) {
      return (CopyOfBuilder) super.moreInfo(moreInfo);
    }
  }
  
}
