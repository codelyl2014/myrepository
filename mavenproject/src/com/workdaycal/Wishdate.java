package com.workdaycal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public final class Wishdate {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@SuppressWarnings({ "deprecation" })
	public List<Date> total(String start, String end, List<Date> holidays, List<Date> weekendatworkdays)
			throws ParseException {
		List<Date> whole = new ArrayList<Date>();
		int startday = Integer.parseInt(start.substring(8, 10));
		int endday = Integer.parseInt(end.substring(8, 10));
		int startmonth = Integer.parseInt(start.substring(5, 7));
		int endmonth = Integer.parseInt(end.substring(5, 7));
		int startyear = Integer.parseInt(start.substring(0, 4));
		int endyear = Integer.parseInt(end.substring(0, 4));
		String str = "";
		String ymdstr = "";
		String str1 = "";
		String ymdstr1 = "";
		Boolean flag = false;
		Boolean flagwk = false;
		Boolean flagresult = false;
		String monthdayString = "";
		String monthString = "";
		String yearmonthdayString = "";

		// 取时间区间内所有的日期 除去节假日 如果在特定日期内加入List，不在则取正常工作日放入List
		if (startyear != endyear) {
			if (startmonth > endmonth) {
				Calendar calendarmonth = Calendar.getInstance();
				calendarmonth.set(Calendar.MONTH, startmonth - 1);
				int lastday = calendarmonth.getActualMaximum(Calendar.DAY_OF_MONTH);
				for (int i = startday; i < lastday + 1; i++) {
					if (i < 10) {
						monthdayString = start.substring(5, 7) + "0" + i;
					} else if (i >= 10) {
						monthdayString = start.substring(5, 7) + i;
					}
					Date day = sdf.parse(start.substring(0, 4) + "-" + monthdayString.substring(0, 2) + "-"
							+ monthdayString.substring(2, 4));
					yearmonthdayString = start.substring(0, 4) + monthdayString;
					// 处理节假日
					for (Date holiday : holidays) {
						str = sdf.format(holiday);
						ymdstr = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
						if (yearmonthdayString != ymdstr && !yearmonthdayString.equals(ymdstr)) {
							flag = true;
						} else {
							flag = false;
							break;
						}
					}
					// 处理特别日期
					if (flag) {
						for (Date week : weekendatworkdays) {
							str1 = sdf.format(week);
							ymdstr1 = str1.substring(0, 4) + str1.substring(5, 7) + str1.substring(8, 10);
							if (yearmonthdayString == ymdstr1 || yearmonthdayString.equals(ymdstr1)) {
								flagwk = true;
								break;
							}
							if (yearmonthdayString != ymdstr1) {
								flagwk = false;
							}
						}
						// 处理周末
						if (!flagwk) {
							if (day.getDay() == 6 || day.getDay() == 0) {
								flagresult = false;
							} else {
								flagresult = true;
								whole.add(day);
								// System.out.println(day);
							}
						}
						if (flagwk) {
							whole.add(day);
							// System.out.println(day);
						}
					}
				}
				while (startmonth != 12) {
					startmonth++;
					if (startmonth < 10) {
						monthString = "0" + startmonth;
					} else if (startmonth >= 10) {
						monthString = "" + startmonth;
					}
					Calendar calendarmonth1 = Calendar.getInstance();
					calendarmonth1.set(Calendar.MONTH, startmonth - 1);
					int lastday1 = calendarmonth1.getActualMaximum(Calendar.DAY_OF_MONTH);
					for (int j = lastday1; j > 0; j--) {
						if (j < 10) {
							monthdayString = monthString + "0" + j;
						} else if (j >= 10) {
							monthdayString = monthString + j;
						}
						Date day = sdf.parse(start.substring(0, 4) + "-" + monthdayString.substring(0, 2) + "-"
								+ monthdayString.substring(2, 4));
						yearmonthdayString = start.substring(0, 4) + monthdayString;
						// 处理节假日
						for (Date holiday : holidays) {
							str = sdf.format(holiday);
							ymdstr = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
							if (yearmonthdayString != ymdstr && !yearmonthdayString.equals(ymdstr)) {
								flag = true;
							} else {
								flag = false;
								break;
							}
						}
						// 处理特别日期
						if (flag) {
							for (Date week : weekendatworkdays) {
								str1 = sdf.format(week);
								ymdstr1 = str1.substring(0, 4) + str1.substring(5, 7) + str1.substring(8, 10);
								if (yearmonthdayString == ymdstr1 || yearmonthdayString.equals(ymdstr1)) {
									flagwk = true;
									break;
								}
								if (yearmonthdayString != ymdstr1) {
									flagwk = false;
								}
							}
							// 处理周末
							if (!flagwk) {
								if (day.getDay() == 6 || day.getDay() == 0) {
									flagresult = false;
								} else {
									flagresult = true;
									whole.add(day);
									// System.out.println(day);
								}
							}
							if (flagwk) {
								whole.add(day);
								// System.out.println(day);
							}
						}
					}
				}
				for (int k = endday; k > 0; k--) {
					if (k < 10) {
						monthdayString = end.substring(5, 7) + "0" + k;
					} else if (k >= 10) {
						monthdayString = end.substring(5, 7) + k;
					}
					Date day = sdf.parse(end.substring(0, 4) + "-" + monthdayString.substring(0, 2) + "-"
							+ monthdayString.substring(2, 4));
					yearmonthdayString = end.substring(0, 4) + monthdayString;
					// 处理节假日
					for (Date holiday : holidays) {
						str = sdf.format(holiday);
						ymdstr = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
						if (yearmonthdayString != ymdstr && !yearmonthdayString.equals(ymdstr)) {
							flag = true;
						} else {
							flag = false;
							break;
						}
					}
					// 处理特别日期
					if (flag) {
						for (Date week : weekendatworkdays) {
							str1 = sdf.format(week);
							ymdstr1 = str1.substring(0, 4) + str1.substring(5, 7) + str1.substring(8, 10);
							if (yearmonthdayString == ymdstr1 || yearmonthdayString.equals(ymdstr1)) {
								flagwk = true;
								break;
							}
							if (yearmonthdayString != ymdstr1) {
								flagwk = false;
							}
						}
						// 处理周末
						if (!flagwk) {
							if (day.getDay() == 6 || day.getDay() == 0) {
								flagresult = false;
							} else {
								flagresult = true;
								whole.add(day);
								// System.out.println(day);
							}
						}
						if (flagwk) {
							whole.add(day);
							// System.out.println(day);
						}
					}
				}
				while (endmonth != 1) {
					endmonth--;
					if (endmonth < 10) {
						monthString = "0" + endmonth;
					} else if (endmonth >= 10) {
						monthString = "" + endmonth;
					}
					Calendar calendarmonth2 = Calendar.getInstance();
					calendarmonth2.set(Calendar.MONTH, endmonth - 1);
					int lastday2 = calendarmonth2.getActualMaximum(Calendar.DAY_OF_MONTH);
					for (int l = lastday2; l > 0; l--) {
						if (l < 10) {
							monthdayString = monthString + "0" + l;
						} else if (l >= 10) {
							monthdayString = monthString + l;
						}
						Date day = sdf.parse(end.substring(0, 4) + "-" + monthdayString.substring(0, 2) + "-"
								+ monthdayString.substring(2, 4));
						yearmonthdayString = end.substring(0, 4) + monthdayString;
						// 处理节假日
						for (Date holiday : holidays) {
							str = sdf.format(holiday);
							ymdstr = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
							if (yearmonthdayString != ymdstr && !yearmonthdayString.equals(ymdstr)) {
								flag = true;
							} else {
								flag = false;
								break;
							}
						}
						// 处理特别日期
						if (flag) {
							for (Date week : weekendatworkdays) {
								str1 = sdf.format(week);
								ymdstr1 = str1.substring(0, 4) + str1.substring(5, 7) + str1.substring(8, 10);
								if (yearmonthdayString == ymdstr1 || yearmonthdayString.equals(ymdstr1)) {
									flagwk = true;
									break;
								}
								if (yearmonthdayString != ymdstr1) {
									flagwk = false;
								}
							}
							// 处理周末
							if (!flagwk) {
								if (day.getDay() == 6 || day.getDay() == 0) {
									flagresult = false;
								} else {
									flagresult = true;
									whole.add(day);
									// System.out.println(day);
								}
							}
							if (flagwk) {
								whole.add(day);
								// System.out.println(day);
							}
						}
					}

				}
			}

		} else if (startyear == endyear) {
			if (startmonth < endmonth) {
				Calendar calendarmonth = Calendar.getInstance();
				calendarmonth.set(Calendar.MONTH, startmonth - 1);
				int lastday = calendarmonth.getActualMaximum(Calendar.DAY_OF_MONTH);
				for (int i = startday; i < lastday + 1; i++) {
					if (i < 10) {
						monthdayString = start.substring(5, 7) + "0" + i;
					} else if (i >= 10) {
						monthdayString = start.substring(5, 7) + i;
					}
					Date day = sdf.parse(start.substring(0, 4) + "-" + monthdayString.substring(0, 2) + "-"
							+ monthdayString.substring(2, 4));
					yearmonthdayString = start.substring(0, 4) + monthdayString;
					// 处理节假日
					for (Date holiday : holidays) {
						str = sdf.format(holiday);
						ymdstr = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
						if (yearmonthdayString != ymdstr && !yearmonthdayString.equals(ymdstr)) {
							flag = true;
						} else {
							flag = false;
							break;
						}
					}
					// 处理特别日期
					if (flag) {
						for (Date week : weekendatworkdays) {
							str1 = sdf.format(week);
							ymdstr1 = str1.substring(0, 4) + str1.substring(5, 7) + str1.substring(8, 10);
							if (yearmonthdayString == ymdstr1 || yearmonthdayString.equals(ymdstr1)) {
								flagwk = true;
								break;
							}
							if (yearmonthdayString != ymdstr1) {
								flagwk = false;
							}
						}
						// 处理周末
						if (!flagwk) {
							if (day.getDay() == 6 || day.getDay() == 0) {
								flagresult = false;
							} else {
								flagresult = true;
								whole.add(day);
								// System.out.println(day);
							}
						}
						if (flagwk) {
							whole.add(day);
							// System.out.println(day);
						}
					}
				}
				startmonth++;
				while (startmonth != endmonth) {
					Calendar calendarmonth1 = Calendar.getInstance();
					calendarmonth1.set(Calendar.MONTH, startmonth - 1);
					int lastday1 = calendarmonth1.getActualMaximum(Calendar.DAY_OF_MONTH);
					if (startmonth < 10) {
						monthString = "0" + startmonth;
					} else if (startmonth >= 10) {
						monthString = "" + startmonth;
					}
					for (int j = lastday1; j > 0; j--) {
						if (j < 10) {
							monthdayString = monthString + "0" + j;
						} else if (j >= 10) {
							monthdayString = monthString + j;
						}
						Date day = sdf.parse(start.substring(0, 4) + "-" + monthdayString.substring(0, 2) + "-"
								+ monthdayString.substring(2, 4));
						yearmonthdayString = start.substring(0, 4) + monthdayString;
						// 处理节假日
						for (Date holiday : holidays) {
							str = sdf.format(holiday);
							ymdstr = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
							if (yearmonthdayString != ymdstr && !yearmonthdayString.equals(ymdstr)) {
								flag = true;
							} else {
								flag = false;
								break;
							}
						}
						// 处理特别日期
						if (flag) {
							for (Date week : weekendatworkdays) {
								str1 = sdf.format(week);
								ymdstr1 = str1.substring(0, 4) + str1.substring(5, 7) + str1.substring(8, 10);
								if (yearmonthdayString == ymdstr1 || yearmonthdayString.equals(ymdstr1)) {
									flagwk = true;
									break;
								}
								if (yearmonthdayString != ymdstr1) {
									flagwk = false;
								}
							}
							// 处理周末
							if (!flagwk) {
								if (day.getDay() == 6 || day.getDay() == 0) {
									flagresult = false;
								} else {
									flagresult = true;
									whole.add(day);
									// System.out.println(day);
								}
							}
							if (flagwk) {
								whole.add(day);
								// System.out.println(day);
							}
						}
					}
					startmonth++;
				}
				if (startmonth == endmonth) {
					if (endmonth < 10) {
						monthString = "0" + endmonth;
					} else if (endmonth >= 10) {
						monthString = "" + endmonth;
					}
					for (int j = endday; j > 0; j--) {
						if (j < 10) {
							monthdayString = monthString + "0" + j;
						} else if (j >= 10) {
							monthdayString = monthString + j;
						}
						Date day = sdf.parse(start.substring(0, 4) + "-" + monthdayString.substring(0, 2) + "-"
								+ monthdayString.substring(2, 4));
						yearmonthdayString = start.substring(0, 4) + monthdayString;
						// 处理节假日
						for (Date holiday : holidays) {
							str = sdf.format(holiday);
							ymdstr = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
							if (yearmonthdayString != ymdstr && !yearmonthdayString.equals(ymdstr)) {
								flag = true;
							} else {
								flag = false;
								break;
							}
						}
						// 处理特别日期
						if (flag) {
							for (Date week : weekendatworkdays) {
								str1 = sdf.format(week);
								ymdstr1 = str1.substring(0, 4) + str1.substring(5, 7) + str1.substring(8, 10);
								if (yearmonthdayString == ymdstr1 || yearmonthdayString.equals(ymdstr1)) {
									flagwk = true;
									break;
								}
								if (yearmonthdayString != ymdstr1) {
									flagwk = false;
								}
							}
							// 处理周末
							if (!flagwk) {
								if (day.getDay() == 6 || day.getDay() == 0) {
									flagresult = false;
								} else {
									flagresult = true;
									whole.add(day);
									// System.out.println(day);
								}
							}
							if (flagwk) {
								whole.add(day);
								// System.out.println(day);
							}
						}
					}
				}

			} else if (startmonth == endmonth) {
				// 起始和截至为同一个月
				for (int i = 0; i < (1 + endday - startday); i++) {
					if (i == 0) {
						monthdayString = start.substring(5, 7) + start.substring(8, 10);
					} else if (Integer.parseInt(start.substring(8, 10)) + i < 10) {
						monthdayString = start.substring(5, 7) + "0" + (Integer.parseInt(start.substring(8, 10)) + i);
					} else if (Integer.parseInt(start.substring(8, 10)) + i >= 10) {
						monthdayString = start.substring(5, 7) + (Integer.parseInt(start.substring(8, 10)) + i);
					}
					Date day = sdf.parse(start.substring(0, 4) + "-" + monthdayString.substring(0, 2) + "-"
							+ monthdayString.substring(2, 4));
					yearmonthdayString = start.substring(0, 4) + monthdayString;

					// 处理节假日
					for (Date holiday : holidays) {
						str = sdf.format(holiday);
						ymdstr = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
						if (yearmonthdayString != ymdstr && !yearmonthdayString.equals(ymdstr)) {
							flag = true;
						} else {
							flag = false;
							break;
						}
					}
					// 处理特别日期
					if (flag) {
						for (Date week : weekendatworkdays) {
							str1 = sdf.format(week);
							ymdstr1 = str1.substring(0, 4) + str1.substring(5, 7) + str1.substring(8, 10);
							if (yearmonthdayString == ymdstr1 || yearmonthdayString.equals(ymdstr1)) {
								flagwk = true;
								break;
							}
							if (yearmonthdayString != ymdstr1) {
								flagwk = false;
							}
						}
						// 处理周末
						if (!flagwk) {
							if (day.getDay() == 6 || day.getDay() == 0) {
								flagresult = false;
							} else {
								flagresult = true;
								whole.add(day);
							}
						}
						if (flagwk) {
							whole.add(day);
						}
					}
				}
			}
		}
		return whole;
	}

	// 节假日
	public List<Date> initHoliday() throws ParseException {
		List<Date> holidays = new ArrayList<Date>();
		SimpleDateFormat sdfholi = new SimpleDateFormat("yyyy-MM-dd");
		// 元旦
		holidays.add(sdfholi.parse("2016-01-01"));
		holidays.add(sdfholi.parse("2016-01-02"));
		holidays.add(sdfholi.parse("2016-01-03"));
		// 春节
		holidays.add(sdfholi.parse("2016-02-07"));
		holidays.add(sdfholi.parse("2016-02-08"));
		holidays.add(sdfholi.parse("2016-02-09"));
		holidays.add(sdfholi.parse("2016-02-10"));
		holidays.add(sdfholi.parse("2016-02-11"));
		holidays.add(sdfholi.parse("2016-02-12"));
		holidays.add(sdfholi.parse("2016-02-13"));
		//
		holidays.add(sdfholi.parse("2016-04-02"));
		holidays.add(sdfholi.parse("2016-04-03"));
		holidays.add(sdfholi.parse("2016-04-04"));
		//
		holidays.add(sdfholi.parse("2016-04-30"));
		holidays.add(sdfholi.parse("2016-05-01"));
		holidays.add(sdfholi.parse("2016-05-02"));
		//
		holidays.add(sdfholi.parse("2016-06-09"));
		holidays.add(sdfholi.parse("2016-06-10"));
		holidays.add(sdfholi.parse("2016-06-11"));
		//
		holidays.add(sdfholi.parse("2016-09-15"));
		holidays.add(sdfholi.parse("2016-09-16"));
		holidays.add(sdfholi.parse("2016-09-17"));
		// 国庆节
		holidays.add(sdfholi.parse("2016-10-01"));
		holidays.add(sdfholi.parse("2016-10-02"));
		holidays.add(sdfholi.parse("2016-10-03"));
		holidays.add(sdfholi.parse("2016-10-04"));
		holidays.add(sdfholi.parse("2016-10-05"));
		holidays.add(sdfholi.parse("2016-10-06"));
		holidays.add(sdfholi.parse("2016-10-07"));

		holidays.add(sdfholi.parse("2017-01-01"));
		holidays.add(sdfholi.parse("2017-01-02"));

		holidays.add(sdfholi.parse("2017-01-27"));
		holidays.add(sdfholi.parse("2017-01-28"));
		holidays.add(sdfholi.parse("2017-01-29"));
		holidays.add(sdfholi.parse("2017-01-30"));
		holidays.add(sdfholi.parse("2017-01-31"));
		holidays.add(sdfholi.parse("2017-02-01"));
		holidays.add(sdfholi.parse("2017-02-02"));
		return holidays;
	}

	// 特殊日期
	public List<Date> weekendAtWorkDays() throws ParseException {
		List<Date> weekendatworkdays = new ArrayList<Date>();
		SimpleDateFormat sdfwork = new SimpleDateFormat("yyyy-MM-dd");

		weekendatworkdays.add(sdfwork.parse("2016-02-06"));
		weekendatworkdays.add(sdfwork.parse("2016-02-14"));

		weekendatworkdays.add(sdfwork.parse("2016-06-12"));

		weekendatworkdays.add(sdfwork.parse("2016-09-18"));

		weekendatworkdays.add(sdfwork.parse("2016-10-08"));
		weekendatworkdays.add(sdfwork.parse("2016-10-09"));

		weekendatworkdays.add(sdfwork.parse("2017-01-22"));
		weekendatworkdays.add(sdfwork.parse("2017-02-04"));
		return weekendatworkdays;
	}

//	public static void main(String[] args) throws Exception {
//		List<Date> holidays = new ArrayList<Date>();
//		List<Date> weekendatworkdays = new ArrayList<Date>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Wishdate wish = new Wishdate();
//		holidays = wish.initHoliday();
//		weekendatworkdays = wish.weekendAtWorkDays();
//		String dutydays = "";
//
//		try {
//			List<Date> strList = wish.total("2016-12-01", "2017-02-28", holidays, weekendatworkdays);
//			System.out.println("工作日是 " + strList.size() + "天");
//			for (Date list : strList) {
//				dutydays = sdf.format(list);
//				System.out.println(dutydays);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}