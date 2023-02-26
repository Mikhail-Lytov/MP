class PriorityQueue:
    __queue = []

    def checking_for_emptiness(self):  # проверка очереди на пустоту
        if len(self.__queue) == 0:
            return True
        else: return False


    def number_in_queue(self):  # число элементов в очереди
        return len(self.__queue)

    def add_items(self, element):  # добавление элементов
        self.__queue.append(element)
        size = len(self.__queue) - 1
        parent = (size - 1) // 2
        while size > 0 and self.__queue[parent] < self.__queue[size]:
            temp = self.__queue[size]
            self.__queue[size] = self.__queue[parent]
            self.__queue[parent] = temp

            size = parent
            parent = (size - 1) // 2
        print(self.__queue)

    def remove_item(self, index):  # удаление из очереди
        self.__queue[index] = -1
        while True:
            left_child = 2 * index + 1
            right_child = 2 * index + 2
            largest_child = index

            if left_child < len(self.__queue) and self.__queue[left_child] > self.__queue[largest_child]:
                largest_child = left_child

            if right_child < len(self.__queue) and self.__queue[right_child] > self.__queue[largest_child]:
                largest_child = right_child

            if largest_child == index:
                break

            temp = self.__queue[index]
            self.__queue[index] = self.__queue[largest_child]
            self.__queue[largest_child] = temp
            index = largest_child
        self.__queue.pop(index)


    def access_maximum_item(self):  # доступ к максимальному элементу очереди
        return self.__queue[0]


    def print_tree(self):
        degree = 0
        size = len(self.__queue) - 1
        if size % 2 == 1:
            degree += 1
        while size > 0:
            degree += 1
            size //= 2
        left = 0
        right = 0
        left = degree ** 2 // 2
        right = left
       ### if(len(self.__queue) % 2 == 1):
       ##     left = len(self.__queue) // 2 + 1
        ##    right = left
        ##else:
          ##  left = len(self.__queue) // 2
            ##right = left
        item = 0
        for i in range(0, degree):
            for j in range(0, 2 ** i):
                print("  " * left + str(self.__queue[item]) + "  " * right, end="" )
                if item + 1 < len(self.__queue):
                    item += 1
                else:
                    break
            print()
            left //= 2
            right = left






