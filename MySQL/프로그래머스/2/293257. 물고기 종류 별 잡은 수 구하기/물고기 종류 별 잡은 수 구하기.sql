-- 코드를 작성해주세요

select COUNT(fi.ID) 'FISH_COUNT', fni.FISH_NAME 'FISH_NAME'
from FISH_INFO fi
join FISH_NAME_INFO fni
on fi.FISH_TYPE = fni.FISH_TYPE
group by fni.FISH_NAME
order by COUNT(fi.ID) desc;

# select fi.CNT 'FISH_COUNT', fni.FISH_NAME 'FISH_NAME'
# from FISH_NAME_INFO fni
# join (
#     select FISH_TYPE, COUNT(ID) 'CNT'
#     from FISH_INFO
#     group by FISH_TYPE
# ) fi
# on fi.FISH_TYPE = fni.FISH_TYPE
# order by fi.CNT desc;