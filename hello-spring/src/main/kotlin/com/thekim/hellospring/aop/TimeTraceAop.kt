package com.thekim.hellospring.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class TimeTraceAop {

    @Around("execution(* com.thekim.hellospring.service..*(..))")
    fun execute(joinPoint: ProceedingJoinPoint): Any{ //메소드가 호출될 때마다, joinPoint 에 걸림
        val start = System.currentTimeMillis()
        println("START : ${joinPoint.toString()}")
        try{
            return joinPoint.proceed()
        } finally {
            val finish = System.currentTimeMillis()
            val timeMs = finish - start
            println("END : ${joinPoint.toString()} ${timeMs}ms")
        }


    }
}