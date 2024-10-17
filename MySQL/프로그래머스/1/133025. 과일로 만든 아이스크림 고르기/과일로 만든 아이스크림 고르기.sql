select ii.flavor
from icecream_info ii join first_half fh
on ii.flavor = fh.flavor
where total_order > 3000 and ingredient_type = 'fruit_based';