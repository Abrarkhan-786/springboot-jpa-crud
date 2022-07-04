package com.springbootmysql.crud.constant;

public class GlobalConstant {
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_MM_dd_yyyy = "MM-dd-yyyy";
	public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
	public static final String EE_d_MMM_yyyy ="EE d MMM yyyy";
	public static final String dd_MMM_yyyy ="dd MMM yyyy";
	public static final String DATETIME_FORMAT2 = "yyyy/MM/dd HH:mm:ss";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String REVIEW_DATE_FORMAT = "dd MMM, yyyy";
	public static final String DATETIME_FORMAT3 = "dd/MM/yyyy HH:mm:ss";
	public static final String ORDER_DATE_FORMAT = "dd/MM/yyyy";
	
	
	public static final String EMAIL_REGIX = "^[A-Za-z0-9._%+-]+@[a-z0-9._-]+\\.[a-z]{2,4}$";
	public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
	public static final String NAME_REGEX = "^(?>[a-zA-Z0-9\\s]*)$";
	public static final String ALL_CHARACTERS_ALLOWED_REGEX = "^([-a-zA-Z0-9/\\''()#*&+{}|`~:;=@?<>%!$\\[\\]_,.\\s\\\"]+)$";
	public static final String ONLY_ALPHABATE_WITHOUT_TAILING_LEADING_SPACES_REGIX="^[^\\s][A-Za-z\\s]*[^\\s]$";
	public static final String ONLY_ALPHA_NUMERIC_WITHOUT_TAILING_LEADING_SPACES_REGIX="^[^\\s][A-Za-z0-9\\s]*[^\\s]$";
	public static final String TIME_REGIX = "^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])(:[0-5][0-9])?$";//24 hours ,second is optional,23:59
	public static final String DATA_REGIX ="^(0|([1-9][0-9]*))(\\.[0-9]+)?(\\s(MB|GB|KB|Bytes))?$";
	public static final String ADDRESS_REGEX = "^([a-zA-Z0-9\\s]*)$";
	public static final String ONLY_ALPHA_NUMERIC_SPECIAL_CHAR = "^[A-Za-z0-9_@\\.#&+\\-,]*$";
	public static final String ONLY_ALPHA_NUMERIC_SPECIAL_CHAR_SPACE = "^[ A-Za-z0-9_@./#&+-]*$";
	public static final String ONLY_NUMERIC = "^[0-9]+$";
	public static final String INTEGER_FLOATING_REGIX = "^(0|([1-9][0-9]*))(\\.[0-9]+)?$";
	public static final String ONLY_NUMERIC_WITHDECIMAL ="^[+-]?([0-9]*[.])?[0-9]{1,2}+$";
	public static final String ALPHA_NUMERIC_WITH_SPECIAL_CHAR ="^[ a-zA-Z0-9!@#$&()\\\\-`'.+,\\/\\\"]*$";
	public static final String MOBILE_NO_TESTER_REGIX ="^\\d{10}$";
	public static final String MOBILE_NO_REGIX_ALL ="^(\\+([1-9]?)[1-9]([1-9]?)[\\s]?)?\\d{10}$";
	
	public static final String FILEPATH_REGIX="^[A-Za-z]:?[A-Za-z0-9\\!\\@\\#\\$\\%\\^\\&\\(\\)\\'\\;\\*\\{\\}\\[\\]\\=\\+\\-\\_\\~\\`\\.\\\\]+$";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String YES = "yes";
	public static final String NO = "no";
	public static final int ZERO = 0;
	public static final String ZERO_STR = "0";
	public static final String ONE_STR = "1";
	public static final String EMPTY_STRING = "";
	public static final String COMMA = ",";
	public static final char SEPERATOR_COMMA = ',';
	public static final String ENCODING_UTF = "UTF-8";
	public static final String EXTENSION_XLS = ".xls";
	public static final String EXTENSION_XLSX = ".xlsx";
	public static final char SEPERATOR_AND = '&';
	public static final char EQUAL_CHAR = '=';
	public static final String SEPERATOR_COLAN = ":";
	public static final String SEPERATOR_SPACE = " ";
	public static final String SEPERATOR_SLASH = "/";
	public static final String SEPERATOR_STAR = "*";
	public static final String SEPERATOR_UNDERSCORE = "_";
	
	public static final String GLOB_URL= "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})";
	
	public static final String PDF_CONTENT_TYPE = "application/pdf";
	public static final String EXCEL_CONTENT_TYPE = "application/octet-stream";
	
	public static final String SUCCESS_STRING = "Success";
	public static final String ERROR_STRING = "Error";
	public static final int ONE = 1;
	public static final Object NO_CONTENT = null;
	
	public static final String ALLOWED_DECIMAL_POINTS="allowed.decimal.points";
	public static final String EXCEL_TYPE="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
}
