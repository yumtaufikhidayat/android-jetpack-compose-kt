package com.taufik.jetpackcompose.ui.compose.navigation.jetreward.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taufik.jetpackcompose.ui.compose.navigation.jetreward.data.RewardRepository
import com.taufik.jetpackcompose.ui.compose.navigation.jetreward.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: RewardRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderRewards() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderRewards().collect { orderReward ->
                val totalRequiredPoint = orderReward.sumOf { it.reward.requirePoint * it.count }
                _uiState.value = UiState.Success(CartState(orderReward, totalRequiredPoint))
            }
        }
    }

    fun updateOrderReward(rewardId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(rewardId, count).collect { isUpdated ->
                if (isUpdated) getAddedOrderRewards()
            }
        }
    }
}