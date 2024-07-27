package com.example.appication_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class NavigationManager(private val fragmentManager: FragmentManager, private val containerId: Int) {

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    fun popBackStack() {
        fragmentManager.popBackStack()
    }

    fun clearBackStack() {
        if (fragmentManager.backStackEntryCount > 0) {
            val first = fragmentManager.getBackStackEntryAt(0)
            fragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun getBackStackCount(): Int {
        return fragmentManager.backStackEntryCount
    }

    fun remove(currentFragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.remove(currentFragment).commit()
    }

    fun add(fragmentContainer: Int, newInstance: MyFragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(fragmentContainer, newInstance).commit()
    }
}
