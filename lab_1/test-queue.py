from priority_queue import PriorityQueue


pr = PriorityQueue()

print("Проверка на пустоту до записи:", pr.checking_for_emptiness())

pr.add_items(20)
pr.add_items(15)
pr.add_items(11)
pr.add_items(7)
pr.add_items(8)
pr.add_items(6)
pr.add_items(17)
pr.add_items(3)

pr.print_tree()

print("Проверка на пустоту после записи:", pr.checking_for_emptiness())

print("удаление элемента из очереди")
pr.remove_item(3)

pr.print_tree()

print("Максимальный элемент", pr.access_maximum_item())