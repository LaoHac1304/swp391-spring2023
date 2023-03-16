package com.laohac.swp391spring2023.model;

import java.time.LocalDate;

public enum SpecialDay {
    HUNG_KINGS_COMMEMORATION_DAY("Giỗ Tổ Hùng Vương", LocalDate.of(2023, 4, 29)),
    REUNIFICATION_DAY("Ngày Giải Phóng", LocalDate.of(2023, 4, 30)),
    INTERNATIONAL_LABOR_DAY("Ngày Quốc Tế Lao Động", LocalDate.of(2023, 5, 1)),
    NATIONAL_DAY("Quốc Khánh", LocalDate.of(2023, 9, 2)),
    MID_AUTUMN_FESTIVAL("Tết Trung Thu", LocalDate.of(2023, 9, 20)),
    VIETNAMESE_TEACHERS_DAY("Ngày Nhà Giáo Việt Nam", LocalDate.of(2023, 11, 20)),
    CHRISTMAS_DAY("Giáng Sinh", LocalDate.of(2023, 12, 25));

    private final String name;
    private final LocalDate date;

    SpecialDay(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

}
