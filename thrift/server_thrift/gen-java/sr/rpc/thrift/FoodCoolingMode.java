/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package sr.rpc.thrift;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2020-04-25")
public enum FoodCoolingMode implements org.apache.thrift.TEnum {
  MEAT(1),
  OTHERS(2);

  private final int value;

  private FoodCoolingMode(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static FoodCoolingMode findByValue(int value) { 
    switch (value) {
      case 1:
        return MEAT;
      case 2:
        return OTHERS;
      default:
        return null;
    }
  }
}
