package Order_CompositeQuery;

import java.util.Map;
import java.util.Set;

public class order_CompositeQuery {

	public static String get_aCondition_For_MySQL(String columnName, String value) {

		String aCondition = null;

		if ("order_id".equals(columnName) || "order_status".equals(columnName)  || "users_id".equals(columnName) ) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("card_number".equals(columnName) || "order_address".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("order_date".equals(columnName))                          // 用於Oracle的date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_MySQL(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
}
