-- 코드를 작성해주세요
select count(n.FISH_TYPE) `FISH_COUNT`
from FISH_INFO i JOIN FISH_NAME_INFO n
on i.FISH_TYPE = n.FISH_TYPE
where n.FISH_NAME in ("BASS", "SNAPPER");