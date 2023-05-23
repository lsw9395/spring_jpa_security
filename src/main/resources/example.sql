select resident1_.`name`                         as col_0_0_,
       resident1_.`resident_registration_number` as col_1_0_,
       resident1_.`gender_code`                  as col_2_0_,
       sum(case
               when birthdeath0_.`birth_death_type_code` = ? then ?
               else 0
           end)                                  as col_3_0_,
       sum(case
               when birthdeath0_.`birth_death_type_code` = ? then ?
               else 0
           end)                                  as col_4_0_
from `birth_death_report_resident` birthdeath0_
         cross join
     `resident` resident1_
where birthdeath0_.`resident_serial_number` = resident1_.`resident_serial_number`
  and birthdeath0_.`resident_serial_number` = ?
group by birthdeath0_.`resident_serial_number`


select resident1_.`name`                         as col_0_0_,
       resident1_.`resident_registration_number` as col_1_0_,
       resident1_.`gender_code`                  as col_2_0_,
       sum(case
               when birthdeath0_.`birth_death_type_code` = ? then ?
               else 0
           end)                                  as col_3_0_,
       sum(case
               when birthdeath0_.`birth_death_type_code` = ? then ?
               else 0
           end)                                  as col_4_0_
from `birth_death_report_resident` birthdeath0_
         cross join
     `resident` resident1_
where birthdeath0_.`resident_serial_number` = resident1_.`resident_serial_number`
  and birthdeath0_.`resident_serial_number` = ?
group by birthdeath0_.`resident_serial_number`


select resident.name,
       resident.residentRegistrationNumber,
       resident.genderCode,
       sum(case when birthDeathReportResident.pk.birthDeathTypeCode = ?1 then ?2 else 0 end),
       sum(case when birthDeathReportResident.pk.birthDeathTypeCode = ?3 then ?4 else 0 end)
from com.nhnacademy.office.entity.Resident resident
         left join fetch resident.birthDeathReportResidents as birthDeathReportResident
where resident.residentSerialNumber = ?5
group by resident.residentSerialNumber

select resident.name,
       resident.residentRegistrationNumber,
       resident.genderCode,
       sum(case when birthDeathReportResident.pk.birthDeathTypeCode = ?1 then ?2 else 0 end),
       sum(case when birthDeathReportResident.pk.birthDeathTypeCode = ?3 then ?4 else 0 end)
from com.nhnacademy.office.entity.Resident resident
         left join fetch resident.birthDeathReportResidents as birthDeathReportResident
with resident = birthDeathReportResident.resident
where resident.residentSerialNumber = ?5
group by resident.residentSerialNumber

select resident0_.`name`                                                         as col_0_0_,
       resident0_.`resident_registration_number`                                 as col_1_0_,
       resident0_.`gender_code`                                                  as col_2_0_,
       sum(case when birthdeath1_.`birth_death_type_code` = ? then ? else 0 end) as col_3_0_,
       sum(case when birthdeath1_.`birth_death_type_code` = ? then ? else 0 end) as col_4_0_
from `resident` resident0_
         left outer join
     `birth_death_report_resident` birthdeath1_
     on resident0_.`resident_serial_number` = birthdeath1_.`resident_serial_number`
         and (
                resident0_.`resident_serial_number` = birthdeath1_.`resident_serial_number`
            )
where resident0_.`resident_serial_number` = ?
group by resident0_.`resident_serial_number`


select resident1_.`name`                         as col_0_0_,
       resident1_.`resident_registration_number` as col_1_0_,
       resident1_.`gender_code`                  as col_2_0_,
       sum(case
               when birthdeath0_.`birth_death_type_code` = ? then ?
               else 0
           end)                                  as col_3_0_,
       sum(case
               when birthdeath0_.`birth_death_type_code` = ? then ?
               else 0
           end)                                  as col_4_0_
from `birth_death_report_resident` birthdeath0_
         left outer join
     `resident` resident1_ on birthdeath0_.`resident_serial_number` = resident1_.`resident_serial_number`
where birthdeath0_.`resident_serial_number` = ?
group by birthdeath0_.`resident_serial_number`


select resident1_.`name`                         as col_0_0_,
       resident1_.`resident_registration_number` as col_1_0_,
       resident1_.`gender_code`                  as col_2_0_,
       sum(case
               when birthdeath0_.`birth_death_type_code` = ? then ?
               else 0
           end)                                  as col_3_0_,
       sum(case
               when birthdeath0_.`birth_death_type_code` = ? then ?
               else 0
           end)                                  as col_4_0_
from `birth_death_report_resident` birthdeath0_
         right outer join
     `resident` resident1_ on birthdeath0_.`resident_serial_number` = resident1_.`resident_serial_number`
where birthdeath0_.`resident_serial_number` = ?
group by birthdeath0_.`resident_serial_number`