package com.taufik.jetpackcompose.ui.compose.navigation.jetreward.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taufik.jetpackcompose.ui.compose.navigation.jetreward.data.RewardRepository
import com.taufik.jetpackcompose.ui.compose.navigation.jetreward.model.OrderReward
import com.taufik.jetpackcompose.ui.compose.navigation.jetreward.model.Reward
import com.taufik.jetpackcompose.ui.compose.navigation.jetreward.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailRewardViewModel(val repository: RewardRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderReward>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderReward>>
        get() = _uiState

    fun getRewardById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderRewardById(rewardId))
        }
    }

    fun addToCart(reward: Reward, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(reward.id, count)
        }
    }
}