# from math import floor

answers = []

test_cases = int(input())

for i in range(test_cases):
    pages, page_size, memory_accesses = map(int, input().split(" "))

    fifo_swaps = 0
    lru_swaps = 0

    lru_mem = []
    fifo_mem = []

    for j in range(memory_accesses):
        address_accessed = int(input())

        page_num = address_accessed // page_size

        # first deal with fifo
        if page_num not in fifo_mem:
            if len(fifo_mem) < pages:
                fifo_mem.append(page_num)
            else:
                # print(f"Swap page {fifo_mem[0]} with page {page_num}")
                del fifo_mem[0]
                fifo_mem.append(page_num)
                fifo_swaps += 1
        # print(fifo_mem)

        # now deal with lru
        if page_num not in lru_mem:
            if len(lru_mem) < pages:
                lru_mem = [page_num] + lru_mem
            else:
                del lru_mem[-1]
                lru_mem = [page_num] + lru_mem
                lru_swaps += 1
        else:
            del lru_mem[lru_mem.index(page_num)]
            lru_mem = [page_num] + lru_mem
    
    if lru_swaps < fifo_swaps:
        answers.append(f"yes {fifo_swaps} {lru_swaps}")
    else:
        answers.append(f"no {fifo_swaps} {lru_swaps}")

for ans in answers:
    print(ans)