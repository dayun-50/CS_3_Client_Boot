package com.kedu.project.growth_chart;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PregnancyUtil {
	
	 // 40주 = 280일
    public static final int TOTAL_DAYS = 280;

    /**
     * birthDate: 출산예정일(또는 실제 출생일)
     * measureDate: 측정일
     *
     * 반환:
     * - 임신 중일 때(fetal 계산): 양수 정수(주차, 1..40)
     * - 출산 후일 때(infant 계산): 양수 정수(주차, 1..)
     *
     * isFetalMode: true면 fetal 계산(출산예정일 기준 전/후 판단은 서비스가 정함)
     */
    public static int calculateFetalWeek(LocalDate dueDate, LocalDate measureDate) {
        // 임신 시작(0주) = dueDate - 40주
        LocalDate conceptionStart = dueDate.minusWeeks(40);
        long daysPassed = ChronoUnit.DAYS.between(conceptionStart, measureDate); // 지난 일수
        if (daysPassed < 0) daysPassed = 0;
        int week = (int)(daysPassed / 7) + 1;
        if (week < 1) week = 1;
        if (week > 40) week = 40;
        return week;
    }

    public static int calculateInfantWeek(LocalDate birthDate, LocalDate measureDate) {
        long daysPassed = ChronoUnit.DAYS.between(birthDate, measureDate);
        if (daysPassed < 0) daysPassed = 0;
        int week = (int)(daysPassed / 7) + 1;
        return week;
    }

    // 주차의 시작/끝 날짜(포함)
    public static LocalDate[] fetalWeekStartEnd(LocalDate dueDate, int week) {
        LocalDate start = dueDate.minusWeeks(40).plusWeeks(week - 1);
        LocalDate end = start.plusDays(6);
        return new LocalDate[]{start, end};
    }

    public static LocalDate[] infantWeekStartEnd(LocalDate birthDate, int week) {
        LocalDate start = birthDate.plusWeeks(week - 1);
        LocalDate end = start.plusDays(6);
        return new LocalDate[]{start, end};
    }

    // 생후 개월 계산 (round down)
    public static int calculateInfantMonths(LocalDate birthDate, LocalDate targetDate) {
        long months = ChronoUnit.MONTHS.between(birthDate.withDayOfMonth(1), targetDate.withDayOfMonth(1));
        return (int) months;
    }
}
