package javabase.RTTI.annotation;



import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by likoguan on 22/06/17.
 */
public class CSVSerializer {
    private static Set<String> baseTypes = new HashSet<String>();
    static {
        baseTypes.add(int.class.getName());
        baseTypes.add(Integer.class.getName());
        baseTypes.add(short.class.getName());
        baseTypes.add(Short.class.getName());
        baseTypes.add(long.class.getName());
        baseTypes.add(Long.class.getName());
        baseTypes.add(float.class.getName());
        baseTypes.add(Float.class.getName());
        baseTypes.add(double.class.getName());
        baseTypes.add(Double.class.getName());
        baseTypes.add(byte.class.getName());
        baseTypes.add(Byte.class.getName());
        baseTypes.add(char.class.getName());
        baseTypes.add(Character.class.getName());
        baseTypes.add(boolean.class.getName());
        baseTypes.add(Boolean.class.getName());
    }

    public static byte[] serialize(List<? extends Object> objectList) throws Exception {
        if (CollectionUtils.isNotEmpty(objectList)) {
            Field[] fields = objectList.get(0).getClass().getDeclaredFields();
            List<Field> fieldList = filterIgnoredFields(fields);
            if (CollectionUtils.isNotEmpty(fieldList)) {
                StringBuilder sb = new StringBuilder();
                sb.append(serializeHeadRow(fieldList))
                        .append(serializeDataRows(fieldList, objectList));
                return sb.toString().getBytes("UTF-8");
            }
        }
        return null;
    }

    private static String serializeHeadRow(List<Field> fieldList) {
        if (CollectionUtils.isNotEmpty(fieldList)) {
            StringBuilder sb = new StringBuilder();
            for (Field field : fieldList) {
                String fieldName = field.getName().toUpperCase();
                if (field.isAnnotationPresent(CSVField.class)) {
                    fieldName = field.getAnnotation(CSVField.class).name().toUpperCase();
                }
                sb.append(fieldName).append(",");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("\n");
            return sb.toString();
        }
        return null;
    }

    private static String serializeDataRows(List<Field> fieldList, List<? extends Object> objects) throws Exception {
        if (CollectionUtils.isNotEmpty(fieldList)) {
            StringBuilder sb = new StringBuilder();
            for (Object object : objects) {
                for (Field field : fieldList) {
                    String value = getValueAsString(field, object);
                    sb.append(value).append(",");
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.append("\n");
            }
            return sb.toString();
        }
        return null;
    }

    private static List<Field> filterIgnoredFields(Field[] fields) {
        List<Field> fieldList = new ArrayList<Field>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CSVFieldIgnore.class)
                    && field.getAnnotation(CSVFieldIgnore.class).ignore()) {
                continue;
            }
            fieldList.add(field);
        }
        return fieldList;
    }

    private static String getValueAsString(Field field, Object object) throws Exception {
        String valueAsString = null;
        field.setAccessible(true);
        Object value = field.get(object);

        /*if (field.getType().isPrimitive()) {
            valueAsString = value.toString();
        } else {*/
        if (value != null) {
            String fieldTypeName = field.getType().getName();
            if (baseTypes.contains(fieldTypeName)) {
                valueAsString = value.toString();
            } else if (fieldTypeName.equals(String.class.getName())) {
                valueAsString = (String) value;
//            } else if (fieldTypeName.equals(Date.class.getName())) {
//                valueAsString = UTCTime.date2String((Date) value);
            } else if (fieldTypeName.equals(BigDecimal.class.getName())) {
                valueAsString = ((BigDecimal) value).toString();
//            } else if (fieldTypeName.equals(TransactionStatus.class.getName())) {
//                valueAsString = ((TransactionStatus) value).name();
//            } else if (fieldTypeName.equals(VerifyStatus.class.getName())) {
//                valueAsString = ((VerifyStatus) value).name();
            } else {
                throw new Exception("csv serialization don't support type: " + fieldTypeName);
            }
        }

        return valueAsString;
    }

    public static class Auckland {
        @CSVField(name = "xiaoguan")
        private int guan;

        private Integer li;

        @CSVFieldIgnore(ignore = false)
        private String kun;

        @CSVFieldIgnore(ignore = true)
        private String man;

        private Date liko;

        public int getGuan() {
            return guan;
        }

        public void setGuan(int guan) {
            this.guan = guan;
        }

        public Integer getLi() {
            return li;
        }

        public void setLi(Integer li) {
            this.li = li;
        }

        public String getKun() {
            return kun;
        }

        public void setKun(String kun) {
            this.kun = kun;
        }

        public Date getLiko() {
            return liko;
        }

        public void setLiko(Date liko) {
            this.liko = liko;
        }

        public String getMan() {
            return man;
        }

        public void setMan(String man) {
            this.man = man;
        }
    }

    public static void main(String[] args) {
        List<Integer> iList = new ArrayList<Integer>();
        Collections.addAll(iList, 0, 1, 2);
        System.out.println(iList.subList(0, 2));

        Auckland auckland = new Auckland();
        auckland.setGuan(0);
        auckland.setLi(1);
        auckland.setKun("kunkun");
        auckland.setLiko(new Date());
        auckland.setMan("manman");
        List<Auckland> aucklands = null;//new ArrayList<>();
        aucklands.add(auckland);
        aucklands.add(auckland);
        try {
            byte[] fileContent = CSVSerializer.serialize(aucklands);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
