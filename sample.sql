select r.resident_serial_number as '주민', 
		rr.resident_serial_number as '신고대상',
        rr.birth_death_type_code as '신고서명',
        rr.birth_report_qualifications_code as '출생 신고자 자격',
        rr.death_report_qualifications_code as '사망 신고자 자격',  
        rr.email_address as '이메일 주소',
        rr.phone_number as '전화번호'
from resident r
inner join birth_death_report_resident  rr on r.resident_serial_number = rr.report_resident_serial_number
where r.resident_serial_number = 10;


select r.resident_serial_number as '주민', 
		f.family_resident_serial_number as '가족번호',
        f.family_relationship_code as '가족코드'
from resident r 
inner join family_relationship f on r.resident_serial_number = f.base_resident_serial_number;


select r.name,
     sum(case when bd.birth_death_type_code = '사망' then 1 else 0 end) as '사망',
     sum(case when bd.birth_death_type_code = '출생' then 1 else 0 end) as '출생'
     from birth_death_report_resident bd
     right join resident r on r.resident_serial_number = bd.resident_serial_number

     group by r.resident_serial_number;