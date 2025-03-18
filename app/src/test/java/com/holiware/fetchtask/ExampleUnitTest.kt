package com.holiware.fetchtask

import com.holiware.fetchtask.data.Item
import com.holiware.fetchtask.data.ItemRepository
import com.holiware.fetchtask.viewmodel.ItemViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ItemViewModel_Test {
    val testDispatcher = StandardTestDispatcher()
    lateinit var viewModel: ItemViewModel
    lateinit var repository: ItemRepository

    val items = listOf(
        Item(1, 2, "Name 3"),
        Item(1, 1, "Name 1"),
        Item(2, 2, "Name 2")
    )


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        coEvery { repository.getItems() } returns flowOf(items)
        viewModel = ItemViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun get_items_test() {
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.itemListState.value
        assertEquals("Name 3", result[2].name)
    }
}