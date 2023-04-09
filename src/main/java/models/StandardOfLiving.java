package main.java.models;
public enum StandardOfLiving {
    VERY_HIGH,
    ULTRA_LOW,
    NIGHTMARE;
    /**
     * @return all lines of enum
     */
    public static String names(){
        StringBuilder nameList=new StringBuilder();
        for (var forms:values()){
            nameList.append(forms.name()).append("\n");
        }
        return nameList.substring(0,nameList.length()-1);

    }
}